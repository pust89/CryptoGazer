package com.pustovit.cryptogazer.onboarding.tea

import com.pustovit.cryptogazer.domain.model.onboarding.OnboardingCard
import com.pustovit.cryptogazer.domain.model.resource.Resource
import com.pustovit.cryptogazer.tea.Event

sealed interface OnboardingEvent : Event {

    sealed interface UiEvent : OnboardingEvent {
        data class OnTopCardClick(val cardId: String) : UiEvent
    }

    sealed interface DomainEvent : OnboardingEvent {
        data class CardsLoaded(val resource: Resource<List<OnboardingCard>>) : UiEvent
    }
}