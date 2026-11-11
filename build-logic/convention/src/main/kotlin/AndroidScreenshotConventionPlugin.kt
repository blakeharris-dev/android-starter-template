import com.android.build.api.dsl.CommonExtension
import dev.blakeharris.convention.libs
import dev.blakeharris.convention.library
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

/**
 * Adds preview-driven screenshot tests (Roborazzi on Robolectric) to a Compose module.
 * Apply alongside `convention.android.compose` (or `convention.android.feature`).
 */
class AndroidScreenshotConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("io.github.takahirom.roborazzi")

            listOf("com.android.application", "com.android.library").forEach { id ->
                pluginManager.withPlugin(id) {
                    // Robolectric needs the module's resources to render composables.
                    extensions.getByType<CommonExtension>()
                        .testOptions.unitTests.isIncludeAndroidResources = true
                }
            }

            dependencies {
                "testImplementation"(project(":core:testing"))
                "testImplementation"(libs.findBundle("screenshot-testing").get())

                val bom = platform(libs.library("androidx-compose-bom"))
                "testImplementation"(bom)
                "testImplementation"(libs.library("androidx-compose-ui-test-junit4"))
            }
        }
    }
}
