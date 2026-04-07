package com.pustovit.cryptogazer.ui_kit.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.pustovit.cryptogazer.ui_kit.modifier.scaleClick
import com.pustovit.cryptogazer.ui_kit.text.AppText
import com.pustovit.cryptogazer.ui_kit.text.AppTextState
import com.pustovit.cryptogazer.ui_kit.theme_2.AppTheme
import com.pustovit.cryptogazer.ui_kit.theme_2.gradient.AppGradientToken

@Immutable
data class CardState(
    val id: String,
    val title: AppTextState,
    val description: AppTextState,
    val selected: Boolean = false,
    val requiredSize: DpSize = DpSize(width = 256.dp, height = 256.dp),
) {
    internal val borderColorToken = if (selected) {
        AppGradientToken.SelectedBorderGradient
    } else {
        AppGradientToken.Transparent
    }
}

sealed interface CardEvent {
    data class Click(val id: String) : CardEvent
}

@Composable
fun Card(
    state: CardState,
    onEvent: (CardEvent) -> Unit,
    modifier: Modifier = Modifier,
) {

    Box(
        modifier = modifier
            .scaleClick { onEvent.invoke(CardEvent.Click(id = state.id)) }
            .requiredSize(state.requiredSize)
            .clip(shape = AppTheme.shapes.roundedCornerShape)
            .background(color = AppTheme.palette.backgroundCard)
            .border(
                border = BorderStroke(
                    width = 4.dp,
                    brush = state.borderColorToken.themedGradient
                ),
                shape = AppTheme.shapes.roundedCornerShape
            )
            .padding(16.dp)
    )
    {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .blur(radius = 4.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
        )

        Column(modifier = Modifier.fillMaxSize()) {
            AppText(
                state = state.title,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            AppText(
                state = state.description,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }

}
