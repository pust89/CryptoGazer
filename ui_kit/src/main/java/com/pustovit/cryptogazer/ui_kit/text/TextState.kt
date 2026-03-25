package com.pustovit.cryptogazer.ui_kit.text

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.pustovit.cryptogazer.ui_kit.theme_2.palette.AppColorToken
import com.pustovit.cryptogazer.ui_kit.theme_2.type.AppTypography

data class TextState(
    val text: String,
    val color: AppColorToken = AppColorToken.TextPrimary,
    val style: TextStyle = AppTypography.bodyMedium,
)

@Composable
fun AppText(state: TextState, modifier: Modifier) {
    BasicText(
        modifier = modifier,
        text = state.text,
        style = state.style.merge(color = state.color.themedColor),
    )
}
