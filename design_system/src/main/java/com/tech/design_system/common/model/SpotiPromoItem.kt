package com.tech.design_system.common.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class SpotiPromoItem(
    val title: String,
    val description: String,
    val buttonText: String,
    val startColor: Color,
    val endColor: Color,
    val imageUrl: String? = null,
    val imageRes: Int? = null,
    val lottieRes: Int? = null,
    val icon: ImageVector? = null
)