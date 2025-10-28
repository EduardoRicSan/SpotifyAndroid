package com.tech.bbvachallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tech.core.remote.NetworkResult
import com.tech.domain.model.Artist
import com.tech.domain.usecase.GetArtistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ArtistViewModel @Inject constructor(
    private val getArtistUseCase: GetArtistUseCase
) : BaseViewModel<Artist>(
    fetchUseCase = { getArtistUseCase() }
)

/*data class ArtistScreenState(
    val isLoading: Boolean = false,
    val artists: List<Artist> = emptyList(),
    val errorMessage: String? = null
)

@HiltViewModel
class ArtistViewModel @Inject constructor(
    private val getArtistUseCase: GetArtistUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ArtistScreenState())
    val uiState: StateFlow<ArtistScreenState> = _uiState.asStateFlow()

    fun loadArtists() {
        viewModelScope.launch(Dispatchers.IO) {
            getArtistUseCase().collect { result ->
                _uiState.update { current ->
                    when(result) {
                        is NetworkResult.Loading -> current.copy(isLoading = true, errorMessage = null)
                        is NetworkResult.Success -> current.copy(
                            isLoading = false,
                            artists = result.data,
                            errorMessage = null
                        )
                        is NetworkResult.Error -> current.copy(
                            isLoading = false,
                            errorMessage = result.message
                        )
                        else -> current
                    }
                }
            }
        }
    }
} */