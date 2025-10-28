package com.tech.design_system.components.box

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


/**
 * [AppScreenBox] is a generic container based on [Box] that serves as the root layout
 * for any screen in the app.
 *
 * This composable helps standardize the structure of your screens
 * by providing configurable options such as:
 *
 * @param modifier Modifier to extend or customize the layout externally.
 * @param backgroundColor Screen background color. Defaults to [Color.White].
 * @param padding Internal margin applied to the content. Defaults to 16.dp.
 * @param contentAlignment Defines how the children inside the Box are aligned.
 *                         Defaults to [Alignment.TopStart].
 * @param showSystemBarsPadding If true, applies [padding] to respect system bars (status & nav bar).
 * @param content Composable content that will be rendered inside the Box.
 *
 * ### Recommended Usage
 * Use [AppScreenBox] as the root container for each screen in your app
 * to keep consistency in backgrounds, spacing, and alignment.
 *
 * Example:
 * ```
 * AppScreenBox(
 *     backgroundColor = MaterialTheme.colorScheme.background,
 *     contentAlignment = Alignment.Center
 * ) {
 *     Text("Welcome 🎉")
 * }
 * ```
 */
@Composable
fun SpotiBox(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White,          // Color de fondo de la pantalla
    padding: Dp = 16.dp,                           // Padding interno por defecto
    contentAlignment: Alignment = Alignment.TopStart, // Alineación del contenido
    showSystemBarsPadding: Boolean = true,         // Para decidir si respetar status bar/nav bar
    cornerTopStart: Dp = 0.dp,
    cornerTopEnd: Dp = 0.dp,
    cornerBottomEnd: Dp = 0.dp,
    cornerBottomStart: Dp = 0.dp,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(
                RoundedCornerShape(
                    topStart = cornerTopStart,
                    topEnd = cornerTopEnd,
                    bottomEnd = cornerBottomEnd,
                    bottomStart = cornerBottomStart
                )
            )
            .background(backgroundColor)
            .then(
                if (showSystemBarsPadding) Modifier.padding(padding)
                else Modifier
            ),
        contentAlignment = contentAlignment
    ) {
        content()
    }
}