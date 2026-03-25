package com.pustovit.cryptogazer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.pustovit.cryptogazer.onboarding.OnboardingScreen
import com.pustovit.cryptogazer.ui_kit.theme_2.CryptoGazerTheme

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