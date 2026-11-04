plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.convention.hilt)
    alias(libs.plugins.androidx.room3)
}

android {
    namespace = "dev.blakeharris.template.core.database"
}

room3 {
    // Exported schemas are committed so migrations can be validated and auto-generated.
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    implementation(project(":core:common"))

    implementation(libs.androidx.room3.runtime)
    implementation(libs.androidx.sqlite.bundled)
    ksp(libs.androidx.room3.compiler)

    androidTestImplementation(libs.kotlinx.coroutines.test)
}
