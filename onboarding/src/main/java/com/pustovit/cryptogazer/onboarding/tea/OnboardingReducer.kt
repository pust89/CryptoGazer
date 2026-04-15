package com.pustovit.cryptogazer.onboarding.tea

import com.pustovit.cryptogazer.tea.Reducer
import com.pustovit.cryptogazer.tea.Update
import com.pustovit.cryptogazer.onboarding.tea.OnboardingState as State
import com.pustovit.cryptogazer.onboarding.tea.OnboardingSideEffect as SideEffect
import com.pustovit.cryptogazer.onboarding.tea.OnboardingCommand as Command

class OnboardingReducer constructor() : Reducer<
        State,
        OnboardingEvent,
        SideEffect,
        Command> {
    override fun reduce(event: OnboardingEvent): Update<State, SideEffect, Command> {
        return when(event){
            is OnboardingEvent.DomainEvent.CardsLoaded -> reduce(event)
            is OnboardingEvent.UiEvent.OnTopCardClick -> reduce(event)
        }
    }

    private fun reduce(event: OnboardingEvent.UiEvent): Update<State, SideEffect, Command>{
        return Update.nothing()
    }

    private fun reduce(event: OnboardingEvent.DomainEvent): Update<State, SideEffect, Command>{
        return Update.nothing()
    }
}