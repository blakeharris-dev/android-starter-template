import com.android.build.api.dsl.CommonExtension
import dev.blakeharris.convention.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

/** Apply alongside `convention.android.application` or `convention.android.library`. */
class AndroidComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

            // Wait for whichever Android plugin the module uses, so plugin order doesn't matter.
            listOf("com.android.application", "com.android.library").forEach { id ->
                pluginManager.withPlugin(id) {
                    configureAndroidCompose(extensions.getByType<CommonExtension>())
                }
            }
        }
    }
}
