package com.pustovit.cryptogazer.onboarding.tea

import com.pustovit.cryptogazer.domain.model.onboarding.OnboardingCard
import com.pustovit.cryptogazer.domain.model.resource.Resource
import com.pustovit.cryptogazer.tea.Reducer
import com.pustovit.cryptogazer.tea.Update
import com.pustovit.cryptogazer.onboarding.tea.OnboardingState as State
import com.pustovit.cryptogazer.onboarding.tea.OnboardingSideEffect as SideEffect
import com.pustovit.cryptogazer.onboarding.tea.OnboardingCommand as Command

class OnboardingReducer : Reducer<
        State,
        OnboardingEvent,
        SideEffect,
        Command> {

    override fun reduce(state: State, event: OnboardingEvent): Update<State, SideEffect, Command> {
        return when (event) {
            is OnboardingEvent.DomainEvent -> reduceDomainEvent(state, event)
            is OnboardingEvent.UiEvent -> reduceUiEvent(state, event)
        }
    }

    private fun reduceUiEvent(
        state: State,
        event: OnboardingEvent.UiEvent
    ): Update<State, SideEffect, Command> {
        return when (event) {
            is OnboardingEvent.UiEvent.OnTopCardClick -> reduceOnTopCardClick(state, event)
        }
    }

    private fun reduceOnTopCardClick(
        state: State,
        event: OnboardingEvent.UiEvent.OnTopCardClick
    ): Update<State, SideEffect, Command> {
        return Update.from(
            state = state.copy(
                selectedCardId = event.cardId,
            )
        )
    }

    private fun reduceDomainEvent(
        state: State,
        event: OnboardingEvent.DomainEvent
    ): Update<State, SideEffect, Command> {
        return when (event) {
            is OnboardingEvent.DomainEvent.CardsLoaded -> reduceCardsLoaded(state, event)
        }
    }

    private fun reduceCardsLoaded(
        state: State,
        event: OnboardingEvent.DomainEvent.CardsLoaded
    ): Update<State, SideEffect, Command> {
        return when (val resource = event.resource) {
            is Resource.Error -> state.copy(loading = false, error = true)
            Resource.Loading -> state.copy(loading = true, error = false)
            is Resource.Success<List<OnboardingCard>> -> state.copy(
                loading = false,
                cards = resource.data
            )
        }.run { Update.from(state = this) }
    }
}