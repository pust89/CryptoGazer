package com.pustovit.cryptogazer.onboarding.di

import android.util.Log
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

interface Deps {
    companion object {
        val empty: Deps = object : Deps {}
    }
}

interface Comp

interface OnboardingDeps : Deps {
    val onboardingRepository: OnboardingRepository
}

@OnboardingScope
@Component
abstract class OnboardingComponent(val dependencies: OnboardingDeps):Comp {

    @Provides
    fun providesOnboardingCommandHandler(): OnboardingCommandHandler {
        return OnboardingCommandHandler(
            onboardingRepository = dependencies.onboardingRepository,
        )
    }

}

interface ComponentHolder<D : Deps> {

    fun <D : Deps> getComponent(dependencies: D): ComponentHolder<D> {
        Log.d("Tag2", "simpleName: ${this.javaClass.simpleName}")
        this.javaClass.fields.javaClass.fields.forEach {
            Log.d("Tag2", "simpleName: $it")
        }
        return this as ComponentHolder<D>
    }
}

object OnboardingComponentHolder : ComponentHolder<OnboardingDeps> {
    override fun <D : Deps> getComponent(dependencies: D): ComponentHolder<D> {
        return super.getComponent(dependencies)
    }
}