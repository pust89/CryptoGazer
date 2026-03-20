package com.pustovit.cryptogazer.ui_kit.theme_2

import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import com.pustovit.cryptogazer.ui_kit.theme_2.palette.CryptoGazerPalette
import com.pustovit.cryptogazer.ui_kit.theme_2.palette.CryptoGazerPaletteLight
import com.pustovit.cryptogazer.ui_kit.theme_2.type.CryptoGazerTypography

object CryptoGazerTheme {

    val palette: CryptoGazerPalette
        @Composable @ReadOnlyComposable get() = LocalCryptoGazerPalette.current

    val typography: CryptoGazerTypography
        @Composable @ReadOnlyComposable get() = LocalCryptoGazerTypography.current

    /**
     * Retrieves the current [Shapes] at the call site's position in the hierarchy.
     *
     * @sample androidx.compose.material3.samples.ThemeShapeSample
     */
    val shapes: Shapes
        @Composable @ReadOnlyComposable get() = LocalShapes.current
}

internal val LocalCryptoGazerPalette =
    staticCompositionLocalOf<CryptoGazerPalette> { CryptoGazerPaletteLight }

internal val LocalCryptoGazerTypography = staticCompositionLocalOf { CryptoGazerTypography }
