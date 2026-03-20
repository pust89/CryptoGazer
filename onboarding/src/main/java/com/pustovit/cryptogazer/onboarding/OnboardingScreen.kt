package com.pustovit.cryptogazer.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pustovit.cryptogazer.ui_kit.onboarding.OnboardingTopCard
import com.pustovit.cryptogazer.ui_kit.theme_2.palette.CryptoGazerPaletteLight


@Composable
fun OnboardingScreen() {
    val viewModel = remember { OnboardingViewModel() }
    Surface(
        color = Color.Transparent,
        modifier = Modifier
            .fillMaxSize()
            .background(brush = CryptoGazerPaletteLight.fullScreenGradient)
            .systemBarsPadding()
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent,
            contentColor = Color.Transparent,
            topBar = {
                Text(
                    text = "TopBar",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        ) { paddingValues ->
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues))
            val uiState = viewModel.uiState.collectAsState()
            when (val state = uiState.value) {
                OnboardingState.Loading -> LoadingScreen()
                is OnboardingState.TopCards -> TopCardsScreen(state, viewModel)
            }
        }

    }
}

@Composable
private fun TopCardsScreen(
    uiState: OnboardingState.TopCards,
    viewModel: OnboardingViewModel,
) {
    val needAppearanceAnimation = remember { mutableStateOf(true) }
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
        ) {

            uiState.cards.forEach { cardState ->
                key(cardState.id) {
                    OnboardingTopCard(
                        state = cardState,
                        onEvent = viewModel::onOnboardingTopCardEvent
                    )
                }
            }
        }
    }


    LaunchedEffect(Unit) {
        needAppearanceAnimation.value = false
    }
}

@Composable
private fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .blur(radius = 30.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
    ) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}