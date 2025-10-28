package com.tech.design_system.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun randomSpotifyColor(): Color {
    val colors = listOf(
        Color(0xFF27856A),
        Color(0xFFE91429),
        Color(0xFF1E3264),
        Color(0xFF477D95),
        Color(0xFF8D67AB),
        Color(0xFFBA5D07),
        Color(0xFFB06239),
        Color(0xFF5179A1)
    )
    return colors.random()
}