package com.pustovit.cryptogazer.app

import android.app.Application
import com.pustovit.cryptogazer.data.di.DataComponent
import com.pustovit.cryptogazer.data.di.create
import com.pustovit.cryptogazer.domain.repository.OnboardingRepository
import com.pustovit.cryptogazer.onboarding.di.OnboardingComponent
import com.pustovit.cryptogazer.onboarding.di.OnboardingDeps
import com.pustovit.cryptogazer.onboarding.di.create

class App : Application() {

    private val dataComponent: DataComponent by lazy {
        DataComponent::class.create()
    }

    override fun onCreate() {
        super.onCreate()
        //dataComponent.provideOnboardingRepository()
        initOnboardingComponent(dataComponent)
    }

    private fun initOnboardingComponent(dataComponent: DataComponent) {

        OnboardingComponent::class.create(
            dependencies = object : OnboardingDeps {
                override val onboardingRepository: OnboardingRepository =
                    dataComponent.onboardingRepository
            }
        )
    }
}