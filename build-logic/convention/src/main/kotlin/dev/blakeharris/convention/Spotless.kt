package dev.blakeharris.convention

import com.diffplug.gradle.spotless.SpotlessExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * Formats Kotlin sources and Gradle scripts with ktlint. Rules come from the root `.editorconfig`.
 *
 * `./gradlew spotlessApply` fixes what it can; `spotlessCheck` (part of `check`) fails on the rest.
 */
internal fun Project.configureSpotless() {
    pluginManager.apply("com.diffplug.spotless")

    val ktlintVersion = libs.findVersion("ktlint").get().requiredVersion

    extensions.configure<SpotlessExtension> {
        kotlin {
            target("src/**/*.kt")
            ktlint(ktlintVersion)
        }
        kotlinGradle {
            target("*.gradle.kts")
            ktlint(ktlintVersion)
        }
    }
}
