# Android Starter Template

A multi-module Android starter built with Kotlin, Jetpack Compose, and Hilt. Gradle convention
plugins, core modules, testing tools, and CI are already setup, so a new app can start on features
straight away.

## What's Included

| **Area**         | **What you get**                                                                              |
|------------------|-----------------------------------------------------------------------------------------------|
| **Build**        | Gradle convention plugins in `build-logic`, a version catalog, AGP 9 with built-in Kotlin     |
| **Architecture** | Core modules for data, persistence, logging, analytics and UI, wired together with Hilt       |
| **Testing**      | Shared fakes and rules, preview-driven screenshot tests, accessibility checks                 |
| **Code Quality** | Spotless with ktlint for formatting, detekt for static analysis, Android Lint                 |
| **CI**           | GitHub Actions for commit checks, linting and unit tests on every PR, with Dependabot updates |

**Requirements:** JDK 21 (Gradle downloads it automatically if needed), Android Studio with AGP 9
support. `minSdk` 24, `compileSdk` and `targetSdk` 37.

## Starting a New App

1. On GitHub, click **Use this template > Create a new repository**, then clone it.
2. Preview the rename, then apply it:

```bash
./scripts/rename/check com.example.myapp "My App"
./scripts/rename/apply com.example.myapp "My App"
```

This changes the application ID, the `:app` package and folders, the project name, the app name and
the theme. The core modules keep the `dev.blakeharris.*` packages.

3. Check it builds, and commit:

```bash
./gradlew assembleDebug
git commit -am "chore: rename template to My App"
```

4. Replace the `Example` placeholders in `:core:model`, `:core:database`, and `:core:data` with
your own types. You can delete `scripts/rename` once you're done renaming your app.

## Modules

| **Module**           | **Purpose**                                                            |
|----------------------|------------------------------------------------------------------------|
| `:app`               | The application: `MainActivity`, `MainApplication` and the Hilt graph  |
| `:core:analytics`    | Consent-aware analytics events, delivered through swappable sinks      |
| `:core:common`       | Coroutine dispatcher qualifiers and the app-wide `CoroutineScope`      |
| `:core:data`         | Repositories: the API the rest of the app uses for data                |
| `:core:database`     | Room 3 database, entities and DAOs                                     |
| `:core:datastore`    | Preferences DataStore for app settings                                 |
| `:core:designsystem` | The app theme and @PreviewContent multipreview annotation              |
| `:core:logging`      | A `Logger` interface with a Logcat implementation                      |
| `:core:model`        | Domain models, with no Android dependencies                            |
| `:core:testing`      | Fakes, `MainDispatcherRule`, screenshot and accessibility test helpers |

New modules apply a convention plugin rather than configuring Android themselves:

| **Plugin**                       | **For**                                                                 |
|----------------------------------|-------------------------------------------------------------------------|
| `convention.android.application` | `:app`                                                                  |
| `convention.android.library`     | Android library modules                                                 |
| `convention.jvm.library`         | Pure-Kotlin libraries                                                   |
| `convention.android.compose`     | Adds Jetpack Compose                                                    |
| `convention.hilt`                | Adds Hilt, for modules with a `di/` package                             |
| `convention.android.feature`     | Feature modules: library, Compose, Hilt, and the design system together |
| `convention.android.screenshot`  | Adds Roborazzi screenshot tests                                         |

## Conventions

- **Dependency injection** lives only in each module's `di/` package. Other classes are plain Kotlin
with no Hilt annotations.
- **`internal` by default**, Modules can expose interfaces and models; implementations stay internal.
- **Test names** use backticks in unit tests (`returns defaults when nothing is saved`) and
`function_condition_result` in instrumented tests.
- **Commits** follow [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/) checked
in CI by [Cocogitto](https://docs.cocogitto.io/).

## Common Commands

| Command                          | 	What it does                                                   |
|----------------------------------|----------------------------------------------------------------|
| `./scripts/test/unit`            | 	Runs every module's unit tests                                 |
| `./scripts/lint/kotlin`          | 	Checks formatting (Spotless) and runs detekt                   |
| `./scripts/lint/android`         | 	Runs Android Lint                                              |
| `./gradlew spotlessApply`        | 	Fixes formatting                                               |
| `./gradlew recordRoborazziDebug` | 	Records screenshot baselines, in modules with screenshot tests |
| `./gradlew verifyRoborazziDebug` | 	Checks screenshots against their baseline                      |

CI runs the same scripts, so a passing local run means a passing CI run.

To check commit messages before they're made, install Cocogitto (`brew install cocogitto`) and run
`cog install-hook --all` once.

## Documentation

Guides on architecture, conventions, and testing are all in the [Wiki]().

## License

Released under the [MIT License](LICENSE).
