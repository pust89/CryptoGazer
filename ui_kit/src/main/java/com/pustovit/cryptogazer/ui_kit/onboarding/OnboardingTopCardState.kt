package com.pustovit.cryptogazer.ui_kit.onboarding

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.pustovit.cryptogazer.ui_kit.theme_2.palette.AppColorToken

@Immutable
data class OnboardingTopCardState(
    val id: String,
    val title: String,
    val titleColor: AppColorToken,
    val selected: Boolean = false,
    val description: String = "",
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
    Card(
        modifier = modifier
            .requiredSize(state.requiredSize)
            .clickable { onEvent.invoke(OnboardingTopCardEvent.Click(id = state.id)) }
            .blur(radius = 4.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
            .padding(16.dp)
    ) {
        Text(
            text = state.title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}
