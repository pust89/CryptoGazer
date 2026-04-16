package com.pustovit.cryptogazer.onboarding.tea

import com.pustovit.cryptogazer.onboarding.OnboardingUiState
import com.pustovit.cryptogazer.ui_kit.card.CardState
import com.pustovit.cryptogazer.ui_kit.text.AppTextState
import com.pustovit.cryptogazer.ui_kit.theme_2.type.AppTypography

class OnboardingUiConverter {

    fun convert(state: OnboardingState): OnboardingUiState {
        return when {
            state.loading -> OnboardingUiState.Loading
            else -> getOnboardingUiState(state)
        }
    }

    private fun getOnboardingUiState(
        state: OnboardingState
    ): OnboardingUiState {
        return OnboardingUiState.Details(
            cards = state.cards.map {
                CardState(
                    id = it.id,
                    title = AppTextState(
                        text = it.cardTitle,
                        style = AppTypography.titleMedium
                    ),
                    description = AppTextState(
                        text = it.cardDescription,
                        style = AppTypography.bodyMedium
                    ),
                    selected = it.id == state.selectedCardId
                )
            }
        )
    }
}