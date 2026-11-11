package dev.blakeharris.testing.screenshot

import com.google.testing.junit.testparameterinjector.TestParameterValuesProvider
import sergio.sastre.composable.preview.scanner.android.AndroidComposablePreviewScanner
import sergio.sastre.composable.preview.scanner.android.AndroidPreviewInfo
import sergio.sastre.composable.preview.scanner.core.preview.ComposablePreview

/**
 * Provides every `@Preview` in the package tree of the concrete subclass. Declare the subclass
 * in the module's root test package, so the whole module is scanned.
 *
 * Multipreview annotations such as `@PreviewContent` are expanded into one preview per variant.
 */
abstract class PackagePreviewsProvider : TestParameterValuesProvider() {
    private val namespace = this::class.java.`package`!!.name

    override fun provideValues(context: Context?): List<ComposablePreview<AndroidPreviewInfo>> =
        AndroidComposablePreviewScanner()
            .scanPackageTrees(namespace)
            .getPreviews()
}
