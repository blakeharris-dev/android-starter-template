plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.convention.hilt)
}

android {
    namespace = "dev.blakeharris.template.core.logging"

    buildFeatures {
        // Needed for BuildConfig.DEBUG, which picks the minimum log level.
        buildConfig = true
    }
}

dependencies {
    testImplementation(libs.robolectric)
}
