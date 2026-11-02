import com.android.build.api.dsl.LibraryExtension
import dev.blakeharris.convention.configureKotlinAndroid
import dev.blakeharris.convention.libs
import dev.blakeharris.convention.library
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
            }

            dependencies {
                "testImplementation"(libs.library("junit"))
            }
        }
    }
}
