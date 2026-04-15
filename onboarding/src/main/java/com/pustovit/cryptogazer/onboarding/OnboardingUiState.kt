package com.pustovit.cryptogazer.onboarding

import androidx.compose.runtime.Immutable
import com.pustovit.cryptogazer.ui_kit.card.CardState


@Immutable
sealed interface OnboardingUiState {

    @Immutable
    data object Loading : OnboardingUiState

    @Immutable
    data class Details(
        val cards: List<CardState>,
    ) : OnboardingUiState

}