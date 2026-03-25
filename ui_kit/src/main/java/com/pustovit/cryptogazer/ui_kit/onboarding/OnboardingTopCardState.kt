package com.pustovit.cryptogazer.ui_kit.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
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
import com.pustovit.cryptogazer.ui_kit.text.TextState
import com.pustovit.cryptogazer.ui_kit.theme_2.AppTheme

@Immutable
data class OnboardingTopCardState(
    val id: String,
    val title: TextState,
    val description: TextState,
    val selected: Boolean = false,
    val requiredSize: DpSize = DpSize(width = 256.dp, height = 256.dp),
)

sealed interface OnboardingTopCardEvent {
    data class Click(val id: String) : OnboardingTopCardEvent
}

@Composable
fun OnboardingTopCard(
    state: OnboardingTopCardState,
    onEvent: (OnboardingTopCardEvent) -> Unit,
    modifier: Modifier = Modifier,
) {

    Box(
        modifier = modifier
            .scaleClick { onEvent.invoke(OnboardingTopCardEvent.Click(id = state.id)) }
            .size(state.requiredSize)
            .clip(AppTheme.shapes.roundedCornerShape)
            .background(color = AppTheme.palette.backgroundCard)
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
