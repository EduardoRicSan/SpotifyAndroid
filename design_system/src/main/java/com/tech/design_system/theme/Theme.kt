package com.tech.design_system.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = SpotiGreen,
    secondary = SpotiAccent,
    background = SpotiWhite,
    surface = SpotiWhite,
    onPrimary = SpotiWhite,
    onSecondary = SpotiBlack,
    onBackground = SpotiBlack,
    onSurface = SpotiBlack
)

private val DarkColorScheme = darkColorScheme(
    primary = SpotiGreen,
    secondary = SpotiAccent,
    background = SpotiGray900,
    surface = SpotiGray800,
    onPrimary = SpotiWhite,
    onSecondary = SpotiWhite,
    onBackground = SpotiWhite,
    onSurface = SpotiWhite
)

@Composable
fun SpotiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = SpotiTypography,
        shapes = SpotiShapes,
        content = content
    )
}