package com.pustovit.cryptogazer.ui_kit.theme_2

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

object CryptoGazerTheme {

    /**
     * Retrieves the current [ColorScheme] at the call site's position in the hierarchy.
     *
     * @sample androidx.compose.material3.samples.ThemeColorSample
     */
    val colorScheme: ColorScheme
        @Composable @ReadOnlyComposable get() = LocalColorScheme.current

    /**
     * Retrieves the current [Typography] at the call site's position in the hierarchy.
     *
     * @sample androidx.compose.material3.samples.ThemeTextStyleSample
     */
    val typography: Typography
        @Composable @ReadOnlyComposable get() = LocalTypography.current

    /**
     * Retrieves the current [Shapes] at the call site's position in the hierarchy.
     *
     * @sample androidx.compose.material3.samples.ThemeShapeSample
     */
    val shapes: Shapes
        @Composable @ReadOnlyComposable get() = LocalShapes.current
}