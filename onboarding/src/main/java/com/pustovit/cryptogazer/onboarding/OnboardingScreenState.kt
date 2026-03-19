package com.pustovit.cryptogazer.onboarding

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf


data class OnboardingScreenData(val buttonText: String)

@Stable
class OnboardingScreenController() {

    val buttonTextState = mutableStateOf<String>("")

    fun setScreenData(data: OnboardingScreenData) {
        buttonTextState.value = data.buttonText
    }
}