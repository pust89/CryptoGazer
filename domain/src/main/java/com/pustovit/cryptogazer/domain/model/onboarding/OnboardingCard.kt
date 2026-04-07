package com.pustovit.cryptogazer.domain.model.onboarding

data class OnboardingCard(
    val id: String,
    val cardTitle: String,
    val cardDescription: String,
    val cardContent: String,
    val header: String,
    val text: String,
)