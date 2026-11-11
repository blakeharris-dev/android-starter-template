plugins {
    alias(libs.plugins.convention.android.library)
}

android {
    namespace = "dev.blakeharris.template.core.testing"
}

dependencies {
    // `api` so modules using these helpers get JUnit, coroutines-test and the faked types.
    api(libs.junit)
    api(libs.kotlinx.coroutines.test)

    api(project(":core:analytics"))
    api(project(":core:data"))
    api(project(":core:database"))

    // Compiled against, but not passed on: only modules applying
    // convention.android.screenshot get these, through that plugin.
    compileOnly(libs.bundles.screenshot.testing)
}
