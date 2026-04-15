package com.pustovit.cryptogazer.di

import android.util.Log

interface Comp

interface ComponentHolder<D : Deps> {

    fun <D : Deps> getComponent(dependencies: D): Comp {

        Log.d("Tag2", "simpleName: ${this.javaClass.simpleName}")
        this.javaClass.fields.javaClass.fields.forEach {
            Log.d("Tag2", "simpleName: $it")
        }
        return this as ComponentHolder<D>
    }
}