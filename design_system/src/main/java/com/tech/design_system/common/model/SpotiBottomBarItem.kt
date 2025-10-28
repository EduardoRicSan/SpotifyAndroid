package com.tech.design_system.common.model

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.tech.design_system.R


sealed class BottomBarItem(val route: String, @StringRes val labelResId: Int, val icon: ImageVector) {
    object Home : BottomBarItem(SpotiRoutes.HOME, R.string.mobah_item_nav_bar_home , Icons.Outlined.Home)
    object Search : BottomBarItem(SpotiRoutes.SEARCH, R.string.mobah_item_nav_bar_search, Icons.Outlined.Search)
    object About : BottomBarItem(SpotiRoutes.ABOUT, R.string.mobah_item_nav_bar_about, Icons.Outlined.Info)
}

object SpotiRoutes {
    const val HOME = "home"
    const val SEARCH = "search"
    const val ABOUT = "about"

    const val ALBUMS = "albums/{artistId}"
    const val SONGS = "songs/{albumJson}"
}