package com.pustovit.cryptogazer.onboarding

import androidx.compose.runtime.Immutable
import com.pustovit.cryptogazer.ui_kit.onboarding.OnboardingTopCardState


@Immutable
sealed interface OnboardingState {

    @Immutable
    data object Loading : OnboardingState

    @Immutable
    data class TopCards(val cards: OnboardingTopCardState) : OnboardingState

}