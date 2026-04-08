package com.pustovit.cryptogazer.app

import android.app.Application
import com.pustovit.cryptogazer.data.di.DataComponent
import com.pustovit.cryptogazer.data.di.create

class App : Application() {

    private val dataComponent: DataComponent by lazy {
        DataComponent::class.create()
    }

    override fun onCreate() {
        super.onCreate()
    }
}