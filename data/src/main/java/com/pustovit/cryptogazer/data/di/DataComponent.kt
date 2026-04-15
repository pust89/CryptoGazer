package com.pustovit.cryptogazer.data.di

import com.pustovit.cryptogazer.data.onboarding.OnboardingRepositoryImpl
import com.pustovit.cryptogazer.domain.repository.OnboardingRepository
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import me.tatarka.inject.annotations.Scope

interface DataComponentApi {
    val onboardingRepository: OnboardingRepository
}

@Scope
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER
)
annotation class DataScope

@DataScope
@Component
abstract class DataComponent : DataComponentApi {

    @get:DataScope
   // @get:Provides
    internal abstract val onboardingRepositoryImpl: OnboardingRepositoryImpl

    override val onboardingRepository: OnboardingRepository
        get() = onboardingRepositoryImpl

}