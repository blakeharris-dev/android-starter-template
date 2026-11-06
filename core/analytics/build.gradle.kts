plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.convention.hilt)
}

android {
    namespace = "dev.blakeharris.template.core.analytics"
}

dependencies {
    implementation(project(":core:logging"))
}
