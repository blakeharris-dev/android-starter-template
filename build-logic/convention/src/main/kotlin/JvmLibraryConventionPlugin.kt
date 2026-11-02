import dev.blakeharris.convention.configureKotlinJvm
import dev.blakeharris.convention.libs
import dev.blakeharris.convention.library
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/** For modules with no Android dependency, e.g. `:core:model`. */
class JvmLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.jvm")
            configureKotlinJvm()

            dependencies {
                "testImplementation"(libs.library("junit"))
            }
        }
    }
}
