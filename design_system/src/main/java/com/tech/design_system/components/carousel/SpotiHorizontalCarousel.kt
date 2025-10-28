package com.tech.design_system.components.carousel

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tech.design_system.common.model.SpotiImageSource
import com.tech.design_system.components.image.SpotiImage
import com.tech.design_system.components.text.SpotiBodyText
import com.tech.design_system.components.text.SpotiHeadlineText

data class CarouselItem(
    val imageUrl: String,
    val title: String,
    val subtitle: String,
    val contentDescription: String = ""
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> SpotiHorizontalCarousel(
    items: List<T>,
    modifier: Modifier = Modifier,
    imageUrlProvider: (T) -> String,
    titleProvider: (T) -> String,
    subtitleProvider: (T) -> String = { "" },
    contentDescriptionProvider: (T) -> String? = { null },
    onItemClick: ((T) -> Unit)? = null
) {
    val carouselState = rememberCarouselState { items.size }

    HorizontalMultiBrowseCarousel(
        state = carouselState,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 16.dp),
        preferredItemWidth = 250.dp,
        itemSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) { index ->
        val item = items[index]

        Box(
            modifier = Modifier
                .height(320.dp)
                .maskClip(MaterialTheme.shapes.extraLarge)
                .clickable { onItemClick?.invoke(item) },
            contentAlignment = Alignment.BottomCenter
        ) {
            // Imagen del ítem
            SpotiImage(
                source = SpotiImageSource.Url(imageUrlProvider(item)),
                modifier = Modifier.matchParentSize(),
                contentDescription = contentDescriptionProvider(item),
                contentScale = ContentScale.Crop
            )

            // Overlay con degradado inferior
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.surface.copy(alpha = 0.85f)
                            )
                        )
                    )
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Título principal
                    SpotiHeadlineText(
                        text = titleProvider(item),
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        textAlign = TextAlign.Center
                    )

                    // Subtítulo
                    val subtitle = subtitleProvider(item)
                    if (subtitle.isNotEmpty()) {
                        SpotiBodyText(
                            text = subtitle,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}
