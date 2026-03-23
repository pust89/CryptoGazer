package com.pustovit.cryptogazer.ui_kit.theme_2.gradient

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

interface AppGradient {
    val fullScreenGradient: Brush
}

internal data object AppGradientLight : AppGradient {
    // Background zone
    override val fullScreenGradient: Brush =
        Brush.linearGradient(colors = listOf(Color.Cyan, Color.Magenta, Color.Cyan))


}

internal data object AppGradientDark : AppGradient {
    // Background zone
    override val fullScreenGradient: Brush =
        Brush.linearGradient(colors = listOf(Color.Cyan, Color.Magenta, Color.Cyan))
}