package com.tech.design_system.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.tech.design_system.common.model.SpotiImageSource
import com.tech.design_system.components.image.SpotiImage
import com.tech.design_system.components.text.SpotiBodyText
import com.tech.design_system.components.text.SpotiHeadlineText

/**
 * SpotiFullImageCard
 * - Displays a full image covering the entire card.
 * - Can execute an optional click action.
 */
/**
 * Card with full background image
 * Can use either NetworkImage or ResourceImage
 */
@Composable
fun SpotiFullImageCard(
    modifier: Modifier = Modifier,
    imageUrl: String? = null,
    imageRes: Int? = null,
    title: String? = null,
    subtitle: String? = null,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable { onClick() },
        shape = MaterialTheme.shapes.extraLarge,
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center // Centrar el contenido
        ) {
            when {
                imageUrl != null -> SpotiImage(
                    source = SpotiImageSource.Url(imageUrl),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds // llena toda la card sin espacios
                )
                imageRes != null -> SpotiImage(
                    source = SpotiImageSource.Resource(imageRes),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.5f)
                            )
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 12.dp, start = 16.dp, end = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                title?.let {
                    SpotiHeadlineText(
                        text = it,
                        color = Color.White,
                        maxLines = 1
                    )
                }
                subtitle?.let {
                    SpotiBodyText(
                        text = it,
                        color = Color.White.copy(alpha = 0.9f),
                        maxLines = 1
                    )
                }
            }
        }
    }
}

