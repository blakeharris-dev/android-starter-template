import dev.blakeharris.convention.libs
import dev.blakeharris.convention.library
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * For `:app`, core modules with a `di/` package, and (via `convention.android.feature`)
 * feature modules that use `@HiltViewModel`.
 */
class HiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.google.devtools.ksp")

            dependencies {
                "ksp"(libs.library("hilt-compiler"))
            }

            // Pure-Kotlin modules only get the annotations, no Android runtime.
            pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
                dependencies {
                    "implementation"(libs.library("hilt-core"))
                }
            }

            pluginManager.withPlugin("com.android.base") {
                pluginManager.apply("com.google.dagger.hilt.android")
                dependencies {
                    "implementation"(libs.library("hilt-android"))
                }
            }
        }
    }
}
