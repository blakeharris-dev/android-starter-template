import com.android.build.api.dsl.ApplicationExtension
import dev.blakeharris.convention.configureKotlinAndroid
import dev.blakeharris.convention.libs
import dev.blakeharris.convention.versionInt
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = libs.versionInt("android-targetSdk")
            }
        }
    }
}
