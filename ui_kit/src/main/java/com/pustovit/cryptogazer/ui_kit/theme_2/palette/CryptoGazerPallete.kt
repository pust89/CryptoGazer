package com.pustovit.cryptogazer.ui_kit.theme_2.palette

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

interface CryptoGazerPalette {
    // Text zone
    val textPrimary: Color
    val textSecondary: Color
    val textStaticWhite: Color
    val textStaticBlack: Color
    val textError: Color
    val textWarning: Color
    val textSuccess: Color

    // Background zone
    val fullScreenGradient: Brush
    val backgroundTransparent: Color
    val backgroundPrimary: Color
    val backgroundCard: Color
}

internal data object CryptoGazerPaletteLight : CryptoGazerPalette {
    // Text zone
    override val textPrimary: Color = Color(0xFF0B0B0C)
    override val textSecondary: Color = Color(0xFF212125)
    override val textStaticWhite: Color = Color(0xFFFFFFFF)
    override val textStaticBlack: Color = Color(0xFF000000)
    override val textError: Color = Color(0xFFE91E63)
    override val textWarning: Color = Color(0xFFFF5722)
    override val textSuccess: Color = Color(0xFF4CAF50)

    // Background zone
    override val fullScreenGradient: Brush =
        Brush.linearGradient(colors = listOf(Color.Cyan, Color.Magenta, Color.Cyan))
    override val backgroundTransparent: Color = Color(0x00000000)
    override val backgroundPrimary: Color = Color(0xFFD3D3D9)
    override val backgroundCard: Color = Color(0xFFFFFFFF)

}

internal data object CryptoGazerPaletteDark : CryptoGazerPalette {
    // Text zone
    override val textPrimary: Color = Color(0xFFECE6E6)
    override val textSecondary: Color = Color(0xFF8C8B8B)
    override val textStaticWhite: Color = Color(0xFFFFFFFF)
    override val textStaticBlack: Color = Color(0xFF000000)
    override val textError: Color = Color(0xFFE91E63)
    override val textWarning: Color = Color(0xFFFF5722)
    override val textSuccess: Color = Color(0xFF4CAF50)

    // Background zone
    override val fullScreenGradient: Brush =
        Brush.linearGradient(colors = listOf(Color.Cyan, Color.Magenta, Color.Cyan))
    override val backgroundTransparent: Color = Color(0x00000000)
    override val backgroundPrimary: Color = Color(0xFF1E1D1D)
    override val backgroundCard: Color = Color(0xFF000000)
}