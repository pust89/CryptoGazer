package com.pustovit.cryptogazer.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color


@Composable
fun OnboardingScreen() {
    val viewModel = remember { OnboardingViewModel() }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.sweepGradient(listOf(Color.Cyan, Color.Magenta))
            )
    ) {
        Unit
    }
}