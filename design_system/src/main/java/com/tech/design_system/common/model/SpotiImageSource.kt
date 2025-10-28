package com.tech.design_system.common.model

import androidx.compose.ui.graphics.ImageBitmap

/**
 * Unified model of Image source
 */
sealed class SpotiImageSource {
    data class Url(val url: String) : SpotiImageSource()
    data class Resource(val resId: Int) : SpotiImageSource()
    data class Bitmap(val imageBitmap: ImageBitmap) : SpotiImageSource()
}