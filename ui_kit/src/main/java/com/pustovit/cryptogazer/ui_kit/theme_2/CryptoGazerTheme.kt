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
import com.pustovit.cryptogazer.ui_kit.theme_2.palette.CryptoGazerPalette
import com.pustovit.cryptogazer.ui_kit.theme_2.palette.CryptoGazerPaletteDark
import com.pustovit.cryptogazer.ui_kit.theme_2.palette.CryptoGazerPaletteLight
import com.pustovit.cryptogazer.ui_kit.theme_2.shape.CryptoGazerShape
import com.pustovit.cryptogazer.ui_kit.theme_2.type.CryptoGazerTypography

object CryptoGazerTheme {

    val palette: CryptoGazerPalette
        @Composable @ReadOnlyComposable get() = LocalCryptoGazerPalette.current

    val typography: CryptoGazerTypography
        @Composable @ReadOnlyComposable get() = LocalCryptoGazerTypography.current


    val shapes: CryptoGazerShape
        @Composable @ReadOnlyComposable get() = LocalCryptoGazerShape.current
}

@Composable
fun CryptoGazerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val palette = if (darkTheme) CryptoGazerPaletteDark else CryptoGazerPaletteLight
    CompositionLocalProvider(
        LocalCryptoGazerPalette provides palette,
        LocalCryptoGazerTypography provides CryptoGazerTypography,
        LocalCryptoGazerShape provides CryptoGazerShape,
    ) {
        CryptoGazerProvideTextStyle(value = CryptoGazerTypography.bodyLarge, content = content)
    }
}

@Composable
private fun CryptoGazerProvideTextStyle(value: TextStyle, content: @Composable () -> Unit) {
    val mergedStyle = CryptoGazerLocalTextStyle.current.merge(value)
    CompositionLocalProvider(
        value = CryptoGazerLocalTextStyle provides mergedStyle,
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

internal val LocalCryptoGazerPalette =
    staticCompositionLocalOf<CryptoGazerPalette> { CryptoGazerPaletteLight }

val CryptoGazerLocalTextStyle = compositionLocalOf(structuralEqualityPolicy()) { DefaultTextStyle }

internal val LocalCryptoGazerTypography = staticCompositionLocalOf { CryptoGazerTypography }
internal val LocalCryptoGazerShape = staticCompositionLocalOf { CryptoGazerShape }
