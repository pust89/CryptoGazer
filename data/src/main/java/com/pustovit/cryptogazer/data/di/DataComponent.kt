package com.pustovit.cryptogazer.data.di

import com.pustovit.cryptogazer.data.onboarding.OnboardingRepositoryImpl
import com.pustovit.cryptogazer.domain.repository.OnboardingRepository
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

interface DataComponentApi{
//TODO добавить internal к     OnboardingRepositoryImpl
}

@DataScope
@Component
abstract class DataComponent {

    @DataScope
    @Provides
    protected abstract fun provideOnboardingRepository(
        onboardingRepositoryImpl: OnboardingRepositoryImpl
    ): OnboardingRepository
}