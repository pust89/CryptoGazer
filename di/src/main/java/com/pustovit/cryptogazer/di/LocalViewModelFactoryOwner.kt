package com.pustovit.cryptogazer.di

import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.ViewModelProvider

interface ViewModelFactoryOwner {
    val viewModelFactory: ViewModelProvider.Factory
}

val LocalViewModelFactoryOwner = compositionLocalOf<ViewModelFactoryOwner> {
    error("No ViewModelFactoryOwner provided")
}