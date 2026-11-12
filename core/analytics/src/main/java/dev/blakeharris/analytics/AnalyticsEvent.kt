package dev.blakeharris.analytics

/**
 * Every event the app can track. Add new events here as subtypes.
 *
 * Events are plain typed data. Converting them to a specific format is the job of the
 * [AnalyticsSink] that sends them. Since this is a sealed interface, the sink's `when (event)`
 * fails to compile until it hands every new event.
 *
 * Use stable identifiers written in code rather than on-screen text. Never include personal data.
 */
sealed interface AnalyticsEvent {

    /**
     * A screen was shown.
     *
     * @property screenName The name of the screen shown, e.g. "home"
     * @property screenClass The code that showed the screen, e.g. "HomeScreen"
     */
    data class ScreenView(
        val screenName: String,
        val screenClass: String
    ) : AnalyticsEvent

    /**
     * The user tapped a button or another control worth tracking.
     *
     * @property screenName The screen the control is on, matching its [ScreenView.screenName]
     * @property buttonText What the control does, e.g. "add_user"
     * @property buttonType What kind of control is it
     */
    data class ButtonClick(
        val screenName: String,
        val buttonText: String,
        val buttonType: ButtonType
    ) : AnalyticsEvent {

        /**
         * The kind of control tapped. Required, so every call site chooses deliberately.
         */
        enum class ButtonType {

            /**
             * A standard button.
             */
            BUTTON,

            /**
             * An icon-only button.
             */
            ICON_BUTTON,

            /**
             * A floating action button.
             */
            FAB,

            /**
             * An item in a drop-down or overflow menu.
             */
            MENU_ITEM,

            /**
             * A tab or bottom navigation item.
             */
            TAB,

            /**
             * Tappable text that navigates within the app.
             */
            TEXT_LINK
        }
    }
}
