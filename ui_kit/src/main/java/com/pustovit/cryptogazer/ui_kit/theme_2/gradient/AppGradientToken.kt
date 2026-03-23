package com.pustovit.cryptogazer.ui_kit.theme_2.gradient

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Brush
import com.pustovit.cryptogazer.ui_kit.theme_2.LocalAppGradient

sealed interface AppGradientToken {

    @get:Composable
    val themedGradient: Brush

    data object FullScreenGradient : AppGradientToken {
        override val themedGradient: Brush
            @Composable @ReadOnlyComposable
            get() = LocalAppGradient.current.fullScreenGradient
    }
}