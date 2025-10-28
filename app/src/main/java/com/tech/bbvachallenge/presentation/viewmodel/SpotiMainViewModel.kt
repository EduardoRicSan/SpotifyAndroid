package com.tech.bbvachallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tech.core.local.provider.SpotiTokenProvider
import com.tech.core.remote.NetworkResult
import com.tech.design_system.common.model.SpotiRoutes
import com.tech.domain.usecase.RefreshTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpotiMainViewModel @Inject constructor(
    private val refreshTokenUseCase: RefreshTokenUseCase,
    private val tokenProvider: SpotiTokenProvider
) : ViewModel() {

    // ----------------------------
    // 🔹 UI State
    // ----------------------------
    private val _topBarTitle = MutableStateFlow("")
    val topBarTitle: StateFlow<String> = _topBarTitle.asStateFlow()

    private val _showBottomBar = MutableStateFlow(true)
    val showBottomBar: StateFlow<Boolean> = _showBottomBar.asStateFlow()

    private val _tokenState = MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading)

    init {
        observeTokenExpiration()
    }

    private fun observeTokenExpiration() {
        viewModelScope.launch(Dispatchers.IO) {
            combine(
                tokenProvider.token,
                tokenProvider.expiresAt
            ) { token, expiresAt -> token to expiresAt }
                .collect { (token, expiresAt) ->
                    val now = System.currentTimeMillis()
                    val remainingTime = expiresAt - now

                    when {
                        token.isBlank() -> refreshToken()
                        remainingTime <= 60_000 -> refreshToken()
                        else -> {
                            delay(remainingTime - 30_000)
                            refreshToken()
                        }
                    }
                }
        }
    }

    private fun refreshToken() {
        viewModelScope.launch(Dispatchers.IO) {
            refreshTokenUseCase().collect { result ->
                _tokenState.value = result
            }
        }
    }

    // ----------------------------
    // 🔹 UI Controls
    // ----------------------------
    fun onRouteChanged(route: String?) {
        val safeRoute = route ?: SpotiRoutes.HOME

        _topBarTitle.value = when (safeRoute) {
            SpotiRoutes.HOME -> "Home"
            SpotiRoutes.SEARCH -> "Search"
            SpotiRoutes.ABOUT -> "About"
            SpotiRoutes.ALBUMS -> "Artist Albums"
            SpotiRoutes.SONGS -> "Songs"
            else -> ""
        }

        _showBottomBar.value = safeRoute in listOf(
            SpotiRoutes.HOME,
            SpotiRoutes.SEARCH,
            SpotiRoutes.ABOUT
        )

    }

}
