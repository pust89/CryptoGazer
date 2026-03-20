package com.pustovit.cryptogazer.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pustovit.cryptogazer.ui_kit.onboarding.OnboardingTopCardEvent
import com.pustovit.cryptogazer.ui_kit.onboarding.OnboardingTopCardState
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
            _uiState.emit(OnboardingState.TopCards(cards = getOnboardingTopCards()))
        }
    }

    fun onOnboardingTopCardEvent(event: OnboardingTopCardEvent) {

    }

    private fun getOnboardingTopCards(): List<OnboardingTopCardState> =
        OnboardingTopCards.entries.map {
            OnboardingTopCardState(
                id = it.name,
                title = it.name,
                selected = false,
                description = it.name + it.name,
            )
        }
}