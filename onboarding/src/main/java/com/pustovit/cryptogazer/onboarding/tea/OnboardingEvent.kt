package com.pustovit.cryptogazer.onboarding.tea

sealed interface OnboardingEvent {

    sealed interface UiEvent : OnboardingEvent {
        data class OnTopCardClick(val cardId: String) : UiEvent
    }

    sealed interface DomainEvent : OnboardingEvent {
        data class OnTopCardClick(val cardId: String) : UiEvent
    }
}