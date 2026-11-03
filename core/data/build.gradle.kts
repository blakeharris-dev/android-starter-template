plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.convention.hilt)
}

android {
    namespace = "dev.blakeharris.template.core.data"
}

dependencies {
    // `api` because repository interfaces expose model types to their callers.
    api(project(":core:model"))

    implementation(project(":core:database"))

    testImplementation(libs.kotlinx.coroutines.test)
}
