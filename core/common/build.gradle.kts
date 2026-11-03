plugins {
    alias(libs.plugins.convention.jvm.library)
    alias(libs.plugins.convention.hilt)
}

dependencies {
    // `api` because the provided types (CoroutineDispatcher, CoroutineScope) are part of this module's API.
    api(libs.kotlinx.coroutines.core)

    testImplementation(libs.kotlinx.coroutines.test)
}
