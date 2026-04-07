package com.pustovit.cryptogazer.onboarding

import androidx.compose.runtime.Immutable
import com.pustovit.cryptogazer.ui_kit.card.CardState
import com.pustovit.cryptogazer.ui_kit.text.AppTextState


@Immutable
sealed interface OnboardingState {

    @Immutable
    data object Loading : OnboardingState

    @Immutable
    data class Details(
        val cards: List<CardState>,
    ) : OnboardingState

}