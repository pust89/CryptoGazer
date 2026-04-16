package com.pustovit.cryptogazer.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pustovit.cryptogazer.onboarding.di.OnboardingScope
import com.pustovit.cryptogazer.onboarding.tea.OnboardingCommand
import com.pustovit.cryptogazer.onboarding.tea.OnboardingCommandHandler
import com.pustovit.cryptogazer.onboarding.tea.OnboardingEvent
import com.pustovit.cryptogazer.onboarding.tea.OnboardingReducer
import com.pustovit.cryptogazer.onboarding.tea.OnboardingState
import com.pustovit.cryptogazer.onboarding.tea.OnboardingUiConverter
import com.pustovit.cryptogazer.tea.Store
import com.pustovit.cryptogazer.ui_kit.card.CardEvent
import com.teobaranga.kotlin.inject.viewmodel.runtime.ContributesViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject

@Inject
@ContributesViewModel(OnboardingScope::class)
class OnboardingViewModel(
    val reducer: OnboardingReducer,
    val commandHandler: OnboardingCommandHandler,
    val uiConverter: OnboardingUiConverter,
) : ViewModel() {

    private val _uiState = MutableStateFlow<OnboardingUiState>(OnboardingUiState.Loading)
    val uiState = _uiState.asStateFlow()
    private val uiEvents = MutableSharedFlow<OnboardingEvent>()

    init {
        Store(
            reducer = reducer,
            commandHandler = commandHandler,
            uiEvents = uiEvents,
            scope = viewModelScope,
            initialState = OnboardingState(),
            initialCommands = listOf(OnboardingCommand.LoadCards),
        ).run {
            state
                .onEach { _uiState.emit(uiConverter.convert(it)) }
                .launchIn(viewModelScope)
        }
    }

    fun onOnboardingTopCardEvent(event: CardEvent) {
        viewModelScope.launch {
            val event = when (event) {
                is CardEvent.Click -> OnboardingEvent.UiEvent.OnTopCardClick(event.id)
            }
            uiEvents.emit(event)
        }
    }
}