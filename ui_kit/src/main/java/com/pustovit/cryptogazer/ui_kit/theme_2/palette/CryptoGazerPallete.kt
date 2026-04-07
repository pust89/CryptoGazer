package com.pustovit.cryptogazer.ui_kit.theme_2.palette

import androidx.compose.ui.graphics.Color

interface AppPalette {
    // Text zone
    val textPrimary: Color
    val textSecondary: Color
    val textStaticWhite: Color
    val textStaticBlack: Color
    val textError: Color
    val textWarning: Color
    val textSuccess: Color

    val transparent: Color
    val backgroundPrimary: Color
    val backgroundCard: Color

}

internal data object AppPaletteLight : AppPalette {
    // Text zone
    override val textPrimary: Color = Color(0xFF0B0B0C)
    override val textSecondary: Color = Color(0xFF212125)
    override val textStaticWhite: Color = Color(0xFFFFFFFF)
    override val textStaticBlack: Color = Color(0xFF000000)
    override val textError: Color = Color(0xFFE91E63)
    override val textWarning: Color = Color(0xFFFF5722)
    override val textSuccess: Color = Color(0xFF4CAF50)

    // Background zone
    override val transparent: Color = Color(0x00000000)
    override val backgroundPrimary: Color = Color(0xFFD3D3D9)
    override val backgroundCard: Color = Color(0x80FFFFFF)
}

internal data object AppPaletteDark : AppPalette {
    // Text zone
    override val textPrimary: Color = Color(0xFFECE6E6)
    override val textSecondary: Color = Color(0xFF8C8B8B)
    override val textStaticWhite: Color = Color(0xFFFFFFFF)
    override val textStaticBlack: Color = Color(0xFF000000)
    override val textError: Color = Color(0xFFE91E63)
    override val textWarning: Color = Color(0xFFFF5722)
    override val textSuccess: Color = Color(0xFF4CAF50)

    // Background zone
    override val transparent: Color = Color(0x00000000)
    override val backgroundPrimary: Color = Color(0xFF1E1D1D)
    override val backgroundCard: Color = Color(0x80000000)
}