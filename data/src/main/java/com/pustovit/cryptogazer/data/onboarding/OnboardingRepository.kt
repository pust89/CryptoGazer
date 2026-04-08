package com.pustovit.cryptogazer.data.onboarding

import com.pustovit.cryptogazer.domain.model.onboarding.OnboardingCard
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OnboardingRepository {

    fun getOnboardingCard(): Flow<List<OnboardingCard>> {
        return flow { emit(getMockOnboardingCard()) }
    }

    private fun getMockOnboardingCard(): List<OnboardingCard> = buildList {
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
}