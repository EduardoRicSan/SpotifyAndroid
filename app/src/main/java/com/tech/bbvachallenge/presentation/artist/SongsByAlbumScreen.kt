package com.tech.bbvachallenge.presentation.artist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tech.bbvachallenge.R
import com.tech.bbvachallenge.presentation.viewmodel.SongViewModel
import com.tech.bbvachallenge.presentation.viewmodel.SpotiMainViewModel
import com.tech.design_system.common.model.SpotiImageSource
import com.tech.design_system.common.model.SpotiRoutes
import com.tech.design_system.common.model.SpotiSnackbarMessage
import com.tech.design_system.components.button.SpotiPrimaryButton
import com.tech.design_system.components.card.SpotiSongItem
import com.tech.design_system.components.icon.SpotiIconVector
import com.tech.design_system.components.image.SpotiImage
import com.tech.design_system.components.text.SpotiBodyText
import com.tech.design_system.components.text.SpotiHeadlineText
import com.tech.design_system.components.text.SpotiLabelText
import com.tech.domain.model.Album
import com.tech.domain.model.Song


@Composable
fun SongsByAlbumScreen(
    showTopSnackbar: (SpotiSnackbarMessage) -> Unit = {},
    showLoader: (Boolean) -> Unit = {},
    navigateToSongs: () -> Unit,
    album: Album,
    songViewModel: SongViewModel = hiltViewModel(),
    mainViewModel: SpotiMainViewModel = hiltViewModel()
) {
    val state by songViewModel.uiState.collectAsStateWithLifecycle()

    SideEffect {
        showLoader(state.isLoading)
        state.errorMessage?.let { showTopSnackbar(SpotiSnackbarMessage(it)) }
    }


    LaunchedEffect(album.id) {
        songViewModel.loadData(album.id.orEmpty())
        mainViewModel.onRouteChanged(SpotiRoutes.SONGS)
        mainViewModel
    }

    SpotiAlbumDetailScreen(
        album = album,
        songs = state.data,
        onPlayClick = {}
    )

}

@Composable
fun SpotiAlbumDetailScreen(
    album: Album,
    songs: List<Song>,
    onPlayClick: () -> Unit = {}
) {
    val scrollState = rememberScrollState()

    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
    ) {

        Column(modifier = Modifier.verticalScroll(scrollState)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)
            ) {
                SpotiImage(
                    source = SpotiImageSource.Url(album.images.firstOrNull()?.url.orEmpty()),
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                listOf(Color.Transparent, MaterialTheme.colorScheme.background),
                                startY = 200f
                            )
                        )
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {
                    SpotiHeadlineText(
                        text = album.name.orEmpty(),
                    )
                    val artistName = album.artists.firstOrNull()?.name
                    val year = album.releaseDate?.take(4)
                    val tracks = album.totalTracks
                    SpotiLabelText(
                        text = "$artistName • $year • $tracks",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                FilledIconButton(
                    onClick = onPlayClick,
                    shape = CircleShape,
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    SpotiIconVector(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = stringResource(R.string.play_cd),
                        tint = MaterialTheme.colorScheme.onPrimary,
                    )

                }
                SpotiIconVector(
                    imageVector = Icons.Default.Shuffle,
                    contentDescription = stringResource(R.string.shuffle_cd),
                    tint = MaterialTheme.colorScheme.onBackground,
                )
            }
            songs.forEach { song ->
                SpotiSongItem(
                    imageUrl = album.images.firstOrNull()?.url.orEmpty(),
                    title = song.name.orEmpty(),
                    artist = song.artists.firstOrNull()?.name.orEmpty(),
                    durationMs = song.durationMs?.toLong() ?: 0L
                )
            }

            Spacer(modifier = Modifier.height(90.dp))
        }

    }
}
