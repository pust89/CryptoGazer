package com.pustovit.cryptogazer.di

interface Comp<D : Deps>


interface ComponentHolder<D, C> where D : Deps, C : Comp<D> {
    var component: C?

    fun required(): C {
        return checkNotNull(component)
    }
}