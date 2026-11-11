package dev.blakeharris.testing.accessibility

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.accessibility.enableAccessibilityChecks
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.tryPerformAccessibilityChecks
import com.google.android.apps.common.testing.accessibility.framework.AccessibilityCheckResult.AccessibilityCheckResultType
import com.google.android.apps.common.testing.accessibility.framework.integrations.espresso.AccessibilityValidator
import org.junit.Assume.assumeTrue
import org.junit.rules.TestRule

/** Fails on warnings as well as errors, so issues are fixed before they become errors. */
val strictAccessibilityValidator: AccessibilityValidator
    get() = AccessibilityValidator().setThrowExceptionFor(AccessibilityCheckResultType.WARNING)

/**
 * Shows [content] and runs Google's Accessibility Test Framework checks on it: touch target
 * size, colour contrast, missing labels and so on. Skipped below API 34.
 *
 * ```kotlin
 * @get:Rule
 * val composeRule = createAndroidComposeRule<ComponentActivity>()
 *
 * @Test
 * fun homeScreen_isAccessible() = composeRule.performAccessibilityChecks {
 *     AppTheme { HomeScreen(...) }
 * }
 * ```
 */
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun <R : TestRule, A : ComponentActivity> AndroidComposeTestRule<R, A>.performAccessibilityChecks(
    validator: AccessibilityValidator = strictAccessibilityValidator,
    content: @Composable () -> Unit,
) {
    assumeTrue(
        "Accessibility checks need API 34+",
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE,
    )
    enableAccessibilityChecks(validator)
    setContent(content)
    onRoot().tryPerformAccessibilityChecks()
}
