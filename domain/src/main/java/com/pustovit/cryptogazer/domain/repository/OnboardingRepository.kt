package com.pustovit.cryptogazer.domain.repository

import com.pustovit.cryptogazer.domain.model.onboarding.OnboardingCard
import kotlinx.coroutines.flow.Flow

interface OnboardingRepository {
    fun getOnboardingCard(): Flow<List<OnboardingCard>>
}