package com.pustovit.cryptogazer.ui_kit.theme_2.palette

import androidx.compose.ui.graphics.Color

interface CryptoGazerPalette {
    val textPrimary: Color
    val textSecondary: Color
    val textStaticWhite: Color
    val textStaticBlack: Color
    val textError: Color
    val textWarning: Color
    val textSuccess: Color
}

data object CryptoGazerPaletteLight : CryptoGazerPalette {
    override val textPrimary: Color = Color(0xFF0B0B0C)
    override val textSecondary: Color = Color(0xFF212125)
    override val textStaticWhite: Color = Color(0xFFFFFFFF)
    override val textStaticBlack: Color = Color(0xFF000000)
    override val textError: Color = Color(0xFFE91E63)
    override val textWarning: Color = Color(0xFFFF5722)
    override val textSuccess: Color = Color(0xFF4CAF50)
}