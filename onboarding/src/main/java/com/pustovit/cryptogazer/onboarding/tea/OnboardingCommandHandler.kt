package com.pustovit.cryptogazer.onboarding.tea

import com.pustovit.cryptogazer.domain.model.onboarding.OnboardingCard

class OnboardingCommandHandler {
}


private fun getMockOnboardingDetails(): List<OnboardingCard> = buildList {
    repeat(10) {
        this += OnboardingCard(
            id = "id$it",
            cardTitle = "cardTitle$it",
            cardDescription = "cardDescription$it",
            cardContent = "cardContent$it",
            header = "header$it",
            text = buildString {
                repeat(10) {
                    append("TextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextText\n")
                }
            }
        )
    }
}