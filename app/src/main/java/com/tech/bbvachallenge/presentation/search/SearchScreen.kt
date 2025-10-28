package com.tech.bbvachallenge.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.tech.bbvachallenge.presentation.viewmodel.SearchViewModel
import com.tech.design_system.common.model.SpotiSnackbarMessage
import com.tech.design_system.components.searchBar.SpotiSimpleSearchBar
import com.tech.design_system.components.text.SpotiHeadlineText
import com.tech.design_system.components.text.SpotiLabelText
import com.tech.design_system.extension.randomSpotifyColor
import com.tech.domain.model.CategorieItem

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
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

    val categories = state.data
    var simpleQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp)
    ) {

        Spacer(modifier = Modifier.height(12.dp))

        SpotiHeadlineText(
            text = "Search",
            modifier = Modifier.padding(vertical = 8.dp)
        )

        SpotiSimpleSearchBar(
            query = simpleQuery,
            onQueryChange = { simpleQuery = it },
            onSearch = { println("Buscar: $it") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        SpotiLabelText(
            text = "Browse all",
            style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.onBackground
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )


        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(categories) { category ->
                SpotiCategoryCard(category = category)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

    }

}

@Composable
fun SpotiCategoryCard(category: CategorieItem) {
    val imageUrl = category.icons.firstOrNull()?.url.orEmpty()

    Box(
        modifier = Modifier
            .aspectRatio(1.2f)
            .clip(RoundedCornerShape(8.dp))
            .background(randomSpotifyColor())
    ) {
        Text(
            text = category.name.orEmpty(),
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(12.dp)
        )

        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.BottomEnd)
                .graphicsLayer(
                    rotationZ = 25f,
                    translationX = 16f,
                    translationY = 8f
                ),
            contentScale = ContentScale.Crop
        )
    }
}



