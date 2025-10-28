package com.tech.design_system.components.scafold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.tech.design_system.common.model.SpotiRoutes
import com.tech.design_system.common.model.SpotiSnackbarMessage
import com.tech.design_system.components.box.SpotiLoaderOverlay
import com.tech.design_system.components.loader.SpotiLoadingIndicator
import com.tech.design_system.components.snackbar.SpotiTopSnackbar
import kotlinx.coroutines.delay

/**
 * [AppScaffold] is a generic wrapper around [Scaffold] that provides
 * a flexible structure for screens with a customizable top bar,
 * bottom navigation bar, and content area.
 *
 * This composable is designed to be reusable across the app
 * so that each screen can inject its own UI components.
 *
 * @param modifier Modifier to customize layout externally.
 * @param topBar Optional composable slot for the top app bar.
 * @param bottomBar Optional composable slot for the bottom navigation bar.
 * @param content Main content area of the screen.
 *
 * ### Recommended Usage
 * ```
 * AppScaffold(
 *     topBar = { MyTopBar(title = "Home") },
 *     bottomBar = { MyBottomNavigation() }
 * ) { paddingValues ->
 *     HomeScreenContent(Modifier.padding(paddingValues))
 * }
 * ```
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpotiAppScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (
        PaddingValues,
        (SpotiSnackbarMessage) -> Unit,
        (Boolean) -> Unit,
    ) -> Unit
) {
    val topSnackbarMessages = remember { mutableStateListOf<SpotiSnackbarMessage>() }
    var showLoader by remember { mutableStateOf(false) }

    val triggerLoader: (Boolean) -> Unit = { showLoader = it }

    Box(modifier = modifier.fillMaxSize()) {
        Scaffold(
            topBar = topBar,
            bottomBar = bottomBar
        ) { innerPadding ->
            content(innerPadding, { topSnackbarMessages.add(it) }, triggerLoader, )
        }

        // ---------------- Snackbar ----------------
        if (topSnackbarMessages.isNotEmpty()) {
            val currentMessage = topSnackbarMessages.first()
            val isVisible = remember(currentMessage) { mutableStateOf(true) }

            SpotiTopSnackbar(
                snackbarMessage = currentMessage,
                isVisible = isVisible,
                onDismiss = { topSnackbarMessages.remove(currentMessage) }
            )

            LaunchedEffect(currentMessage) {
                delay(currentMessage.durationMillis ?: 3000L)
                if (isVisible.value) {
                    isVisible.value = false
                    topSnackbarMessages.remove(currentMessage)
                }
            }
        }

        // ---------------- Overlay Loader ----------------
        if (showLoader) {
            SpotiLoaderOverlay {
                SpotiLoadingIndicator() // Puedes reemplazar por cualquier loader
            }
        }

    }
}