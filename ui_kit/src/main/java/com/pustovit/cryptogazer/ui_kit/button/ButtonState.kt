package com.pustovit.cryptogazer.ui_kit.button

import androidx.compose.runtime.Immutable

@Immutable
data class ButtonState(
    val id: String = "",
    val text: String = "",
    val enabled: Boolean = true,
)
