package com.tech.bbvachallenge.navigation

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.tech.bbvachallenge.presentation.about.AboutScreen
import com.tech.bbvachallenge.presentation.artist.ArtistAlbumesDetailScreen
import com.tech.bbvachallenge.presentation.artist.FullArtistScreen
import com.tech.bbvachallenge.presentation.artist.SongsByAlbumScreen
import com.tech.bbvachallenge.presentation.search.SearchScreen
import com.tech.bbvachallenge.presentation.viewmodel.SpotiMainViewModel
import com.tech.design_system.common.model.SpotiRoutes
import com.tech.design_system.components.bottomBar.SpotiBottomBar
import com.tech.design_system.components.scafold.SpotiAppScaffold
import com.tech.design_system.components.topBar.SpotiTopBar
import com.tech.domain.model.Album
import com.tech.domain.model.Song
import kotlinx.serialization.json.Json

@Composable
fun SpotiNavHost(
    modifier: Modifier = Modifier,
    viewModel: SpotiMainViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val topBarTitle by viewModel.topBarTitle.collectAsStateWithLifecycle()
    val showBottomBar by viewModel.showBottomBar.collectAsStateWithLifecycle()

    LaunchedEffect(currentRoute) {
        viewModel.onRouteChanged(currentRoute)
    }

    SpotiAppScaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { SpotiTopBarContent(
            title = topBarTitle,
            navController,
        )
                 },
        bottomBar = { if (showBottomBar) SpotiBottomBar(navController) }
    ) { paddingValues, showTopSnackbar, triggerLoader ->
        NavHost(
            navController = navController,
            startDestination = SpotiRoutes.HOME,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(SpotiRoutes.HOME) {
                FullArtistScreen(
                    showTopSnackbar = showTopSnackbar,
                    showLoader = triggerLoader,
                    navigateToAlbum = { artistId ->
                        navController.navigate("albums/$artistId")
                    }
                )
            }

            composable(SpotiRoutes.SEARCH) {
                SearchScreen(
                    showTopSnackbar = showTopSnackbar,
                    showLoader = triggerLoader,
                    navigateToAlbum = {}
                )
            }
            composable(SpotiRoutes.ABOUT) { AboutScreen() }

            composable(
                route = SpotiRoutes.ALBUMS,
                arguments = listOf(navArgument("artistId") { type = NavType.StringType })
            ) { backStackEntry ->
                val artistID = backStackEntry.arguments?.getString("artistId") ?: ""
                ArtistAlbumesDetailScreen(
                    showTopSnackbar = showTopSnackbar,
                    showLoader = triggerLoader,
                    navigateToSongs = { album ->
                        val json = Uri.encode(Json.encodeToString(album))
                        navController.navigate("songs/$json")
                    },
                    artistId = artistID
                )
            }


            composable(
                route = SpotiRoutes.SONGS,
                arguments = listOf(
                    navArgument("albumJson") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val albumJson = backStackEntry.arguments?.getString("albumJson") ?: ""
                val album = Json.decodeFromString<Album>(Uri.decode(albumJson))

                SongsByAlbumScreen(
                    showTopSnackbar = showTopSnackbar,
                    showLoader = triggerLoader,
                    album = album,
                    navigateToSongs = {}
                )
            }
        }
    }
}

@Composable
fun SpotiTopBarContent(title: String, navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBack = currentRoute != null && currentRoute != SpotiRoutes.HOME

    SpotiTopBar(
        titleText = title,
        showBackButton = showBack,
        onBackClick = { navController.popBackStack() },
        showFirstActionIcon = true,
        onFirstActionClick = { /* TODO */ },
        showSecondActionIcon = true,
        onSecondActionClick = { /* TODO */ },
    )
}

