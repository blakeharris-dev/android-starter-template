#!/usr/bin/env bash
# Renames the template for a new app: application ID, app module package, app name and theme.
#
# Usage:
#   ./scripts/rename/check <application-id> "<App Name>"   # preview, changes nothing
#   ./scripts/rename/apply <application-id> "<App Name>"   # make the changes
#
# Example:
#   ./scripts/rename/check dev.blakeharris.notes "Notes"
#
# This file holds the logic for both commands, so the preview always matches what applying does.
# Run it through `check` or `apply` rather than directly.
#
# Only the app module is renamed. Core modules keep their `dev.blakeharris.<module>` packages,
# which are shared by every app made from the template.
#
# Run it once, on a fresh copy of the template, with no uncommitted changes, so the result can be
# reviewed with `git diff` and undone with `git checkout .` if needed.
set -o errexit -o nounset -o pipefail

readonly OLD_PACKAGE="dev.blakeharris.template"
readonly OLD_NAME="Android Starter Template"
readonly OLD_THEME="AndroidStarterTemplate"

usage() {
    sed -n '2,9p' "$0" | sed 's/^# \{0,1\}//'
    exit "${1:-0}"
}

dry_run=false
args=()
for arg in "$@"; do
    case "$arg" in
        --dry-run) dry_run=true ;;
        -h | --help) usage 0 ;;
        -*) echo "Unknown option: $arg" >&2; usage 1 ;;
        *) args+=("$arg") ;;
    esac
done
[[ ${#args[@]} -eq 2 ]] || usage 1

new_package="${args[0]}"
new_name="${args[1]}"

# --- Validation ---------------------------------------------------------------------------------

# At least two segments, each starting with a lowercase letter: the rules for an application ID.
if [[ ! "$new_package" =~ ^[a-z][a-z0-9_]*(\.[a-z][a-z0-9_]*)+$ ]]; then
    echo "Invalid application ID: '$new_package'" >&2
    echo "Use lowercase segments separated by dots, e.g. dev.blakeharris.notes" >&2
    exit 1
fi
if [[ "$new_package" == "$OLD_PACKAGE" || "$new_package" == "$OLD_PACKAGE".* ]]; then
    echo "The new application ID must not be, or start with, $OLD_PACKAGE" >&2
    exit 1
fi

# "My Notes" -> "MyNotes", used for the theme name `Theme.MyNotes`.
new_theme="$(printf '%s' "$new_name" | perl -pe 's/[^A-Za-z0-9]+/ /g; s/(\w+)/\u$1/g; s/ //g')"
if [[ ! "$new_theme" =~ ^[A-Za-z][A-Za-z0-9]*$ ]]; then
    echo "The app name must start with a letter and contain letters or digits: '$new_name'" >&2
    exit 1
fi

cd "$(git rev-parse --show-toplevel)"

if [[ "$dry_run" == false && -n "$(git status --porcelain)" ]]; then
    echo "There are uncommitted changes. Commit or stash them first, so the rename can be reviewed" >&2
    echo "and undone on its own." >&2
    exit 1
fi

# --- Helpers ------------------------------------------------------------------------------------

run() {
    if [[ "$dry_run" == true ]]; then
        echo "  would run: $*"
    else
        "$@"
    fi
}

# Replaces every occurrence of $1 with $2 in tracked files, except these scripts.
replace_text() {
    local old="$1" new="$2" file
    while IFS= read -r file; do
        echo "  $file: '$old' -> '$new'"
        if [[ "$dry_run" == false ]]; then
            OLD="$old" NEW="$new" perl -pi -e 's/\Q$ENV{OLD}\E/$ENV{NEW}/g' "$file"
        fi
    done < <(git grep -l -F -e "$old" -- . ':!scripts/rename/' || true)
}

# --- Rename -------------------------------------------------------------------------------------

[[ "$dry_run" == true ]] && echo "Checking only: nothing will be changed."
echo
echo "Application ID and package: $OLD_PACKAGE -> $new_package"
replace_text "$OLD_PACKAGE" "$new_package"

echo
echo "App name: $OLD_NAME -> $new_name"
replace_text "$OLD_NAME" "$new_name"

echo
echo "Theme: Theme.$OLD_THEME -> Theme.$new_theme"
replace_text "$OLD_THEME" "$new_theme"

echo
echo "Package folders:"
old_path="${OLD_PACKAGE//.//}"
new_path="${new_package//.//}"
while IFS= read -r old_dir; do
    new_dir="${old_dir%"$old_path"}$new_path"
    echo "  $old_dir -> $new_dir"
    run mkdir -p "$(dirname "$new_dir")"
    run git mv "$old_dir" "$new_dir"
done < <(find app/src -type d -path "*/$old_path" | sort)

if [[ "$dry_run" == false ]]; then
    # Remove folders left empty by the move, e.g. dev/blakeharris when the domain changes.
    find app/src -type d -empty -delete
fi

echo
if [[ "$dry_run" == true ]]; then
    echo "Nothing was changed. To apply:"
    echo "  ./scripts/rename/apply $new_package \"$new_name\""
else
    echo "Done. Next:"
    echo "  1. Review the changes:   git status && git diff"
    echo "  2. Check it builds:      ./gradlew assembleDebug"
    echo "  3. Commit:               git commit -am \"chore: rename template to $new_name\""
fi
