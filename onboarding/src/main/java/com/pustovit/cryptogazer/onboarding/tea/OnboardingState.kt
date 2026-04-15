package com.pustovit.cryptogazer.onboarding.tea

import com.pustovit.cryptogazer.domain.model.onboarding.OnboardingCard

data class OnboardingState(
    val loading: Boolean = true,
    val cards: List<OnboardingCard> = emptyList(),
)