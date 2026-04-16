package com.pustovit.cryptogazer.onboarding.tea

import com.pustovit.cryptogazer.domain.model.onboarding.OnboardingCard

data class OnboardingState(
    val cards: List<OnboardingCard> = emptyList(),
    val selectedCardId: String = "",
    val loading: Boolean = true,
    val error: Boolean = true,
){

}