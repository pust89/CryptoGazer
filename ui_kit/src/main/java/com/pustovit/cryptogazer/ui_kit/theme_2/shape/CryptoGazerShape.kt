package com.pustovit.cryptogazer.ui_kit.theme_2.shape

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

object CryptoGazerShape {
    val rectangle: Shape = RectangleShape
    val roundedCornerShape = RoundedCornerShape(size = 16.dp)
    val roundedTopCornerShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
}