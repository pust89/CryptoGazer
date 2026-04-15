package com.pustovit.cryptogazer.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pustovit.cryptogazer.onboarding.tea.OnboardingCommand
import com.pustovit.cryptogazer.onboarding.tea.OnboardingCommandHandler
import com.pustovit.cryptogazer.onboarding.tea.OnboardingReducer
import com.pustovit.cryptogazer.onboarding.tea.OnboardingState
import com.pustovit.cryptogazer.onboarding.tea.OnboardingUiConverter
import com.pustovit.cryptogazer.tea.Store
import com.pustovit.cryptogazer.ui_kit.card.CardEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject

class OnboardingViewModel @Inject constructor(
    val reducer: OnboardingReducer,
    val commandHandler: OnboardingCommandHandler,
    val uiConverter: OnboardingUiConverter,
) : ViewModel() {

    private val _uiState = MutableStateFlow<OnboardingUiState>(OnboardingUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        Store(
            reducer = reducer,
            commandHandler = commandHandler,
            scope = viewModelScope,
            initialState = OnboardingState(),
            initialCommands = listOf(OnboardingCommand.LoadCards),
        ).run {
            state.onEach(uiConverter::convert).launchIn(viewModelScope)
        }
    }

    fun onOnboardingTopCardEvent(event: CardEvent) = when (event) {
        is CardEvent.Click -> viewModelScope.launch {
            val currentState = uiState.value as? OnboardingUiState.Details ?: return@launch
            _uiState.value = currentState.copy(
                cards = currentState.cards.map { card ->
                    card.copy(
                        selected = card.id == event.id
                    )
                }
            )
        }
    }
}