package dev.blakeharris.testing.screenshot

import com.github.takahirom.roborazzi.ExperimentalRoborazziApi
import com.github.takahirom.roborazzi.RoborazziOptions
import com.github.takahirom.roborazzi.captureRoboImage
import com.github.takahirom.roborazzi.toRoborazziComposeOptions
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestParameterInjector
import org.robolectric.annotation.GraphicsMode
import sergio.sastre.composable.preview.scanner.android.AndroidPreviewInfo
import sergio.sastre.composable.preview.scanner.android.screenshotid.AndroidPreviewScreenshotIdBuilder
import sergio.sastre.composable.preview.scanner.core.preview.ComposablePreview

/**
 * Screenshots one preview, using the configuration declared on the preview itself
 * (light/dark, font scale, device). Subclass once per module with a [PackagePreviewsProvider].
 *
 * Images are saved under `src/test/snapshots/<package path>/<name>.png`.
 */
@RunWith(RobolectricTestParameterInjector::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@OptIn(ExperimentalRoborazziApi::class)
abstract class BaseScreenshotTest(
    private val preview: ComposablePreview<AndroidPreviewInfo>,
) {

    @Test
    fun screenshot() {
        preview.captureRoboImage(
            filePath = screenshotFilePath(),
            roborazziOptions = RoborazziOptions(),
            roborazziComposeOptions = preview.toRoborazziComposeOptions(),
        )
    }

    private fun screenshotFilePath(): String {
        val packagePath = preview.declaringClass
            .substringBeforeLast('.')
            .removePrefix("$PACKAGE_ROOT.")
            .replace('.', '/')
        val className = preview.declaringClass.substringAfterLast('.')
        val configId = AndroidPreviewScreenshotIdBuilder(preview)
            .ignoreClassName()
            .ignoreMethodName()
            .build()
        return "src/test/snapshots/$packagePath/${className}_${preview.methodName}_$configId.png"
    }

    private companion object {
        /** Stripped from snapshot folder paths, so they're relative to the module. */
        const val PACKAGE_ROOT = "dev.blakeharris.template"
    }
}
