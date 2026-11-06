package dev.blakeharris.analytics

sealed interface AnalyticsEvent {
    data class ScreenView(
        val screenName: String,
        val screenClass: String
    ) : AnalyticsEvent

    data class ButtonClick(
        val screenName: String,
        val buttonText: String,
        val buttonType: ButtonType
    ) : AnalyticsEvent {
        enum class ButtonType {
            BUTTON,
            ICON_BUTTON,
            FAB,
            MENU_ITEM,
            TAB,
            TEXT_LINK
        }
    }
}
