package dev.blakeharris.convention

import dev.detekt.gradle.extensions.DetektExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * Static analysis with detekt: complexity, code smells and likely bugs. Starts from detekt's
 * default rules, with the project's overrides in `config/detekt/detekt.yml`.
 *
 * Formatting is Spotless's job, so detekt's formatting rules aren't enabled.
 */
internal fun Project.configureDetekt() {
    pluginManager.apply("dev.detekt")

    extensions.configure<DetektExtension> {
        buildUponDefaultConfig.set(true)
        config.setFrom(rootProject.layout.projectDirectory.file("config/detekt/detekt.yml"))
        parallel.set(true)
    }
}
