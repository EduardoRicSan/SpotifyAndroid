package com.tech.bbvachallenge.presentation.artist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tech.bbvachallenge.R
import com.tech.bbvachallenge.presentation.demos.DemoSpotiPromoCarousel
import com.tech.bbvachallenge.presentation.viewmodel.ArtistViewModel
import com.tech.design_system.common.model.SpotiSnackbarMessage
import com.tech.design_system.components.carousel.SpotiHorizontalCarousel
import com.tech.design_system.components.text.SpotiHeadlineText


@Composable
fun FullArtistScreen(
    viewModel: ArtistViewModel = hiltViewModel(),
    showTopSnackbar: (SpotiSnackbarMessage) -> Unit = {},
    showLoader: (Boolean) -> Unit = {},
    navigateToAlbum: (String) -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) { viewModel.loadData() }

    // SideEffect para loader y snackbar
    SideEffect {
        showLoader(state.isLoading)
        state.errorMessage?.let { showTopSnackbar(SpotiSnackbarMessage(it)) }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        SpotiHeadlineText(
            stringResource(R.string.home_top_artist),
            modifier = Modifier.padding(8.dp)
            )

        SpotiHorizontalCarousel(
            items = state.data,
            imageUrlProvider = { it.images.firstOrNull()?.url.orEmpty() },
            titleProvider = { it.name.orEmpty() },
            subtitleProvider = { it.genres.firstOrNull().orEmpty() },
            onItemClick = { artist ->
                navigateToAlbum(artist.id.orEmpty())
            }
        )
        SpotiHeadlineText(stringResource(R.string.home_for_you), modifier = Modifier.padding(8.dp))
        DemoSpotiPromoCarousel()
    }

}