package com.pustovit.cryptogazer.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pustovit.cryptogazer.ui_kit.card.CardEvent
import com.pustovit.cryptogazer.ui_kit.card.CardState
import com.pustovit.cryptogazer.ui_kit.text.AppTextState
import com.pustovit.cryptogazer.ui_kit.theme_2.type.AppTypography
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OnboardingViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<OnboardingState>(OnboardingState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            delay(2000)
            _uiState.emit(OnboardingState.Details(cards = getOnboardingTopCards()))
        }
    }

    fun onOnboardingTopCardEvent(event: CardEvent) = when (event) {
        is CardEvent.Click -> viewModelScope.launch {
            val currentState = uiState.value as? OnboardingState.Details ?: return@launch
            _uiState.value = currentState.copy(
                cards = currentState.cards.map { card ->
                    card.copy(
                        selected = card.id == event.id
                    )
                }
            )
        }
    }

    private fun getOnboardingTopCards(): List<CardState> =
        OnboardingDetailsCards.entries.map {
            CardState(
                id = it.name,
                title = AppTextState(
                    text = it.name,
                    style = AppTypography.titleMedium,
                ),
                selected = false,
                description = AppTextState(
                    text = it.name + it.name,
                    style = AppTypography.bodyMedium,
                ),
            )
        }
}