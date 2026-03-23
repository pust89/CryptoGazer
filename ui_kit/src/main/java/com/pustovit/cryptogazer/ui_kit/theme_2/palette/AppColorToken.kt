package com.pustovit.cryptogazer.ui_kit.theme_2.palette

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import com.pustovit.cryptogazer.ui_kit.theme_2.LocalAppPalette



sealed interface AppColorToken  {
    @get:Composable
    abstract val themedColor: Color

    data object TextPrimary : AppColorToken {
        override val themedColor: Color
            @Composable @ReadOnlyComposable
            get() = LocalAppPalette.current.textPrimary
    }

    data object TextSecondary : AppColorToken {
        override val themedColor: Color
            @Composable @ReadOnlyComposable
            get() = LocalAppPalette.current.textSecondary
    }

    data object TextStaticWhite : AppColorToken {
        override val themedColor: Color
            @Composable @ReadOnlyComposable
            get() = LocalAppPalette.current.textStaticWhite
    }

    data object TextStaticBlack : AppColorToken {
        override val themedColor: Color
            @Composable @ReadOnlyComposable
            get() = LocalAppPalette.current.textStaticBlack
    }

    data object TextError : AppColorToken {
        override val themedColor: Color
            @Composable @ReadOnlyComposable
            get() = LocalAppPalette.current.textError
    }

    data object TextWarning : AppColorToken {
        override val themedColor: Color
            @Composable @ReadOnlyComposable
            get() = LocalAppPalette.current.textWarning
    }

    data object TextSuccess : AppColorToken {
        override val themedColor: Color
            @Composable @ReadOnlyComposable
            get() = LocalAppPalette.current.textSuccess
    }

    data object BackgroundTransparent : AppColorToken {
        override val themedColor: Color
            @Composable @ReadOnlyComposable
            get() = LocalAppPalette.current.backgroundTransparent
    }

    data object BackgroundPrimary : AppColorToken {
        override val themedColor: Color
            @Composable @ReadOnlyComposable
            get() = LocalAppPalette.current.backgroundPrimary
    }

    data object BackgroundCard : AppColorToken {
        override val themedColor: Color
            @Composable @ReadOnlyComposable
            get() = LocalAppPalette.current.backgroundCard
    }
}