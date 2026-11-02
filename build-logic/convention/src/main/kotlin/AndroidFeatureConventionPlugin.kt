import dev.blakeharris.convention.libs
import dev.blakeharris.convention.library
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Library + Compose + Hilt (for `@HiltViewModel` only — wiring stays in core `di/` packages),
 * plus the dependencies every feature needs.
 */
class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("convention.android.library")
            pluginManager.apply("convention.android.compose")
            pluginManager.apply("convention.hilt")

            dependencies {
                "implementation"(project(":core:ui"))

                "implementation"(libs.library("androidx-lifecycle-runtime-compose"))
                "implementation"(libs.library("androidx-lifecycle-viewmodel-compose"))
                "implementation"(libs.library("androidx-hilt-lifecycle-viewmodel-compose"))

                // Navigation is left out until you pick Navigation Compose vs Navigation 3.
            }
        }
    }
}
