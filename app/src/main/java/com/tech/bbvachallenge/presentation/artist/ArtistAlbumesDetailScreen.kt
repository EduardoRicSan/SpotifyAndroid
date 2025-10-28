package com.tech.bbvachallenge.presentation.artist

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.tech.bbvachallenge.presentation.viewmodel.AlbumViewModel
import com.tech.design_system.common.model.SpotiSnackbarMessage
import com.tech.design_system.components.card.SpotiFullImageCard
import com.tech.domain.model.Album

@Composable
fun ArtistAlbumesDetailScreen(
    showTopSnackbar: (SpotiSnackbarMessage) -> Unit = {},
    showLoader: (Boolean) -> Unit = {},
    navigateToSongs: (Album) -> Unit,
    artistId: String,
    viewModel: AlbumViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    SideEffect {
        showLoader(state.isLoading)
        state.errorMessage?.let { showTopSnackbar(SpotiSnackbarMessage(it)) }
    }

    LaunchedEffect(artistId) {
        viewModel.loadData(artistId)
    }

    LazyColumn {
        items(state.data) { album ->
            SpotiFullImageCard(
                imageUrl = album.images.firstOrNull()?.url,
                title = album.name,
                subtitle = stringResource(R.string.artist_sc_tracks, album.totalTracks.toString() ),
                modifier = Modifier.padding(8.dp),
                onClick = { navigateToSongs(album) }
            )
        }
    }
}