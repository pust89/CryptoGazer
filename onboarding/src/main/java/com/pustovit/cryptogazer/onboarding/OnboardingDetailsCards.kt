package com.pustovit.cryptogazer.onboarding

import kotlin.String

enum class OnboardingDetailsCards {
    One,
    Two,
    Three,
    Four,
    Five,
}

data class OnboardingDetail(
    val id: String,
    val cardTitle: String,
    val cardDescription: String,
    val cardContent: String,
    val header: String,
    val text: String,
)

fun getMockOnboardingDetails(): List<OnboardingDetail> = buildList {
    repeat(10) {
        this += OnboardingDetail(
            id = "id$it",
            cardTitle = "cardTitle$it",
            cardDescription = "cardDescription$it",
            cardContent = "cardContent$it",
            header = "header$it",
            text = buildString {
                repeat(10) {
                    append("TextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextText\n")
                }
            }
        )
    }
}

