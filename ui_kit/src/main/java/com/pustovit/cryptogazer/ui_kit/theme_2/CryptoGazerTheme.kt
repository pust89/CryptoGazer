package com.pustovit.cryptogazer.ui_kit.theme_2

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import com.pustovit.cryptogazer.ui_kit.theme_2.palette.CryptoGazerPalette
import com.pustovit.cryptogazer.ui_kit.theme_2.palette.CryptoGazerPaletteDark
import com.pustovit.cryptogazer.ui_kit.theme_2.palette.CryptoGazerPaletteLight
import com.pustovit.cryptogazer.ui_kit.theme_2.type.CryptoGazerTypography


@Composable
fun CryptoGazerTheme2(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val palette = if (darkTheme) CryptoGazerPaletteDark else CryptoGazerPaletteLight
    val typography = CryptoGazerTypography
    CompositionLocalProvider(
        LocalCryptoGazerPalette provides palette,
        LocalCryptoGazerTypography provides CryptoGazerTypography,
    ) {
        ProvideTextStyle(value = typography.bodyLarge, content = content)
    }
}

object CryptoGazerTheme {

    val palette: CryptoGazerPalette
        @Composable @ReadOnlyComposable get() = LocalCryptoGazerPalette.current

    val typography: CryptoGazerTypography
        @Composable @ReadOnlyComposable get() = LocalCryptoGazerTypography.current

//
//    val shapes: Shapes
//        @Composable @ReadOnlyComposable get() = LocalShapes.current
}


internal val LocalCryptoGazerPalette =
    staticCompositionLocalOf<CryptoGazerPalette> { CryptoGazerPaletteLight }

internal val LocalCryptoGazerTypography = staticCompositionLocalOf { CryptoGazerTypography }
