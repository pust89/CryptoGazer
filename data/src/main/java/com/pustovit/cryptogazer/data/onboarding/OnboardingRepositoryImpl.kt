package com.pustovit.cryptogazer.data.onboarding

import com.pustovit.cryptogazer.domain.model.onboarding.OnboardingCard
import com.pustovit.cryptogazer.domain.repository.OnboardingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.tatarka.inject.annotations.Inject

@Inject
class OnboardingRepositoryImpl : OnboardingRepository {

    override fun getOnboardingCard(): Flow<List<OnboardingCard>> {
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