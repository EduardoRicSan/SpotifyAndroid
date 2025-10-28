package com.tech.design_system.components.carousel

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp
import com.tech.design_system.common.model.SpotiPromoItem
import com.tech.design_system.components.card.SpotiPromoCard
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SpotiPromoCarousel(
    SpotiPromoItems: List<SpotiPromoItem>,
    autoScrollDelay: Long = 3000L // milisegundos
) {
    // Tamaño del contenedor
    val windowInfo = LocalWindowInfo.current
    val density = LocalDensity.current
    val containerWidth = with(density) { windowInfo.containerSize.width.toDp() } - 16.dp

    // Estado del LazyRow
    val lazyListState = rememberLazyListState()

    // Snap scroll
    val flingBehavior = rememberSnapFlingBehavior(
        lazyListState = lazyListState,
        snapPosition = SnapPosition.Start
    )

    // Estado del item actual
    val coroutineScope = rememberCoroutineScope()
    val currentItem by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex
        }
    }

    // Auto-scroll
    LaunchedEffect(key1 = SpotiPromoItems.size) {
        while (true) {
            delay(autoScrollDelay)
            val nextIndex = (lazyListState.firstVisibleItemIndex + 1) % SpotiPromoItems.size
            lazyListState.animateScrollToItem(nextIndex)
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        LazyRow(
            state = lazyListState,
            flingBehavior = flingBehavior,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
           // contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            itemsIndexed(SpotiPromoItems) { index, item ->
                SpotiPromoCard(
                    modifier = Modifier
                        .fillParentMaxWidth(), // 👈 asegura el ancho completo real visible
                    SpotiPromoItem = item,
                )
            }
        }

        // Dots indicators
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(vertical = 12.dp)
        ) {
            SpotiPromoItems.forEachIndexed { index, _ ->
                val isSelected = index == currentItem
                Box(
                    modifier = Modifier
                        .size(if (isSelected) 10.dp else 8.dp)
                        .clip(CircleShape)
                        .background(
                            if (isSelected) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                        )
                        .clickable {
                            coroutineScope.launch {
                                lazyListState.animateScrollToItem(index)
                            }
                        }
                )
            }
        }
    }
}
