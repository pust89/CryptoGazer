package com.pustovit.cryptogazer.onboarding.di

import com.pustovit.cryptogazer.di.Comp
import com.pustovit.cryptogazer.di.Deps
import com.pustovit.cryptogazer.domain.repository.OnboardingRepository
import com.pustovit.cryptogazer.onboarding.tea.OnboardingCommandHandler
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import me.tatarka.inject.annotations.Scope

@Scope
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER
)
annotation class OnboardingScope

interface OnboardingDeps : Deps {
    val onboardingRepository: OnboardingRepository
}

@OnboardingScope
@Component
abstract class OnboardingComponent(val dependencies: OnboardingDeps) : Comp {

    @Provides
    fun providesOnboardingCommandHandler(): OnboardingCommandHandler {
        return OnboardingCommandHandler(
            onboardingRepository = dependencies.onboardingRepository,
        )
    }

}