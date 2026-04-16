package com.pustovit.cryptogazer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.ViewModelProvider
import com.pustovit.cryptogazer.di.LocalViewModelFactoryOwner
import com.pustovit.cryptogazer.di.ViewModelFactoryOwner
import com.pustovit.cryptogazer.onboarding.OnboardingScreen
import com.pustovit.cryptogazer.ui_kit.theme_2.CryptoGazerTheme

// 1. Определите интерфейс владельца фабрики

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                CryptoGazerTheme {
                    OnboardingScreen()
                }
            }
    }
}