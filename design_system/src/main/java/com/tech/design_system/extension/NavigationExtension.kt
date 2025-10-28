package com.tech.design_system.extension

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}
fun NavController.safeNavigate(route: String) {
    this.navigate(route) {
        launchSingleTop = true
        restoreState = true
        popUpTo(this@safeNavigate.graph.startDestinationId) { saveState = true }
    }
}

/**
 * Navigate to a route with singleTop and restoreState by default
 */
fun NavController.navigateSingleTopRestore(route: String) {
    this.navigate(route) {
        launchSingleTop = true
        restoreState = true
    }
}

/**
 * Navigate to a route and clear the back stack up to a specific route
 * Useful for flows like finishing onboarding and returning to main screen
 */
fun NavController.navigateAndClearBackStack(route: String, popUpToRoute: String) {
    this.navigate(route) {
        popUpTo(popUpToRoute) { inclusive = true }
        launchSingleTop = true
        restoreState = true
    }
}

/**
 * Navigate to a route, keeping the back stack, but restoring state if the destination exists
 */
fun NavController.navigateRestore(route: String) {
    this.navigate(route) {
        restoreState = true
    }
}

/**
 * Navigate to a route and pop the current screen from the back stack
 */
fun NavController.navigateReplaceCurrent(route: String) {
    this.navigate(route) {
        popUpTo(this@navigateReplaceCurrent.currentBackStackEntry?.destination?.route ?: route) { inclusive = true }
        launchSingleTop = true
        restoreState = true
    }
}