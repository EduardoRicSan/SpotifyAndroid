package com.tech.design_system.components.card

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.tech.design_system.common.model.SpotiImageSource
import com.tech.design_system.components.icon.SpotiIconVector
import com.tech.design_system.components.image.SpotiImage
import com.tech.design_system.components.text.SpotiHeadlineText
import com.tech.design_system.components.text.SpotiLabelText

@Composable
fun SpotiSongItem(
    modifier: Modifier = Modifier,
    imageUrl: String,
    title: String,
    artist: String,
    durationMs: Long, // duración en milisegundos
    onMoreClick: () -> Unit = {},
    onClick: () -> Unit = {}
) {
    // Convertir ms a minutos y segundos
    val totalSeconds = (durationMs / 1000).toInt()
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SpotiImage(
            source = SpotiImageSource.Url(imageUrl),
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(10.dp))
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            SpotiHeadlineText(
                text = title,
            )
            SpotiLabelText(
                text = artist,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )

            // Mostrar duración
            SpotiLabelText(
                text = String.format("%d:%02d", minutes, seconds),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }

        SpotiIconVector(
            imageVector = Icons.Default.MoreVert,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            onClick = { onMoreClick() }
        )
    }
}




