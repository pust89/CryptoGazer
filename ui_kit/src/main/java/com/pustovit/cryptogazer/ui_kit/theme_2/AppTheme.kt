package com.pustovit.cryptogazer.ui_kit.theme_2

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import com.pustovit.cryptogazer.ui_kit.theme_2.gradient.AppGradient
import com.pustovit.cryptogazer.ui_kit.theme_2.gradient.AppGradientDark
import com.pustovit.cryptogazer.ui_kit.theme_2.gradient.AppGradientLight
import com.pustovit.cryptogazer.ui_kit.theme_2.palette.AppPalette
import com.pustovit.cryptogazer.ui_kit.theme_2.palette.AppPaletteDark
import com.pustovit.cryptogazer.ui_kit.theme_2.palette.AppPaletteLight
import com.pustovit.cryptogazer.ui_kit.theme_2.shape.AppShape
import com.pustovit.cryptogazer.ui_kit.theme_2.type.AppTypography

object AppTheme {

    val palette: AppPalette
        @Composable @ReadOnlyComposable get() = LocalAppPalette.current

    val gradient: AppGradient
        @Composable @ReadOnlyComposable get() = LocalAppGradient.current

    val typography: AppTypography
        @Composable @ReadOnlyComposable get() = LocalAppTypography.current


    val shapes: AppShape
        @Composable @ReadOnlyComposable get() = LocalAppShape.current
}

@Composable
fun CryptoGazerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val palette = if (darkTheme) AppPaletteDark else AppPaletteLight
    val gradient = if (darkTheme) AppGradientDark else AppGradientLight
    CompositionLocalProvider(
        LocalAppPalette provides palette,
        LocalAppGradient provides gradient,
        LocalAppTypography provides AppTypography,
        LocalAppShape provides AppShape,
    ) {
        CryptoGazerProvideTextStyle(value = AppTypography.bodyLarge, content = content)
    }
}

@Composable
private fun CryptoGazerProvideTextStyle(value: TextStyle, content: @Composable () -> Unit) {
    val mergedStyle = LocalAppDefaultTextStyle.current.merge(value)
    CompositionLocalProvider(
        value = LocalAppDefaultTextStyle provides mergedStyle,
        content = content
    )
}


internal val platformStyle = PlatformTextStyle(includeFontPadding = false)

internal val DefaultTextStyle =
    TextStyle.Default.copy(
        platformStyle = platformStyle,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        ),
    )

internal val LocalAppPalette =
    staticCompositionLocalOf<AppPalette> { AppPaletteLight }

internal val LocalAppGradient =
    staticCompositionLocalOf<AppGradient> { AppGradientLight }

val LocalAppDefaultTextStyle = compositionLocalOf(structuralEqualityPolicy()) { DefaultTextStyle }

internal val LocalAppTypography = staticCompositionLocalOf { AppTypography }
internal val LocalAppShape = staticCompositionLocalOf { AppShape }
