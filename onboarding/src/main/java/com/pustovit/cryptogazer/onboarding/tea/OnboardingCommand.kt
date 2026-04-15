package com.pustovit.cryptogazer.onboarding.tea

import com.pustovit.cryptogazer.tea.Command

sealed interface OnboardingCommand : Command {
    data object LoadCards : OnboardingCommand
}