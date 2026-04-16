package com.pustovit.cryptogazer.onboarding.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pustovit.cryptogazer.di.Comp
import com.pustovit.cryptogazer.di.ComponentHolder
import com.pustovit.cryptogazer.di.Deps
import com.pustovit.cryptogazer.domain.repository.OnboardingRepository
import com.pustovit.cryptogazer.onboarding.OnboardingViewModel
import com.pustovit.cryptogazer.onboarding.tea.OnboardingCommandHandler
import com.pustovit.cryptogazer.onboarding.tea.OnboardingReducer
import com.pustovit.cryptogazer.onboarding.tea.OnboardingUiConverter
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
abstract class OnboardingComponent(val dependencies: OnboardingDeps) : Comp<OnboardingDeps> {

    @Provides
    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return when (modelClass) {
                    OnboardingViewModel::class.java -> provideOnboardingViewModel() as T

                    else -> throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
        }
    }

    private fun provideOnboardingViewModel(): OnboardingViewModel {
        return OnboardingViewModel(
            reducer = OnboardingReducer(),
            commandHandler = OnboardingCommandHandler(
                onboardingRepository = dependencies.onboardingRepository,
            ),
            uiConverter =  OnboardingUiConverter(),
        )
    }
}

object OnboardingComponentHolder : ComponentHolder<OnboardingDeps, OnboardingComponent> {
    override var component: OnboardingComponent? = null

}