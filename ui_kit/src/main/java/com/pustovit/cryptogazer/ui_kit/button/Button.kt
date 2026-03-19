package com.pustovit.cryptogazer.ui_kit.button

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun Button(
    state: ButtonState,
    modifier: Modifier = Modifier,
    action: ButtonAction? = null,
) {
    Button(
        modifier = modifier,
        enabled = state.enabled,
        onClick = { action?.onClick(state.id) },
    ) {
        Text(
            text = state.text
        )
    }
}