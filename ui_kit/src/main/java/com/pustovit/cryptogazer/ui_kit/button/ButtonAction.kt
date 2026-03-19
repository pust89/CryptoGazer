package com.pustovit.cryptogazer.ui_kit.button

import androidx.compose.runtime.Stable

@Stable
fun interface ButtonAction {
    fun onClick(id: String)
}