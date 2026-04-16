package com.pustovit.cryptogazer.di

import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Scope

@Scope
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER
)
annotation class AppScope


@Component
abstract class AppComponent{
}