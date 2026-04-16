package com.pustovit.cryptogazer.di

import androidx.lifecycle.ViewModelProvider
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Scope

import me.tatarka.inject.annotations.MergeComponent
import me.tatarka.inject.annotations.SingleIn

@Scope
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER
)
annotation class AppScope


@Component
abstract class AppComponent{
    abstract val vmFactory: ViewModelProvider.Factory
}