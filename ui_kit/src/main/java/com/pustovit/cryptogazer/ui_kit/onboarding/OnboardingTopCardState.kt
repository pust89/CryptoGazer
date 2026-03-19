package com.pustovit.cryptogazer.ui_kit.onboarding

import androidx.compose.foundation.clickable
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier

@Immutable
data class OnboardingTopCardState(
    val id: String,
    val title: String,
    val selected: Boolean = false,
    val description: String = "",
) {
    sealed interface Event {
        data class Click(val id: String) : Event
    }
}

@Composable
fun OnboardingTopCard(
    state: OnboardingTopCardState,
    onEvent: (OnboardingTopCardState.Event) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .clickable { onEvent.invoke(OnboardingTopCardState.Event.Click(id = state.id)) }
    ) {
        Text(
            text = state.title,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}
