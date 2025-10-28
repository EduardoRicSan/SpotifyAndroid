package com.tech.bbvachallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tech.core.remote.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModelParams<T, P>(
    private val fetchUseCase: suspend (P) -> Flow<NetworkResult<List<T>>>
) : ViewModel() {

    private val _uiState = MutableStateFlow(GenericScreenState<T>())
    val uiState: StateFlow<GenericScreenState<T>> = _uiState.asStateFlow()

    fun loadData(param: P) {
        viewModelScope.launch(Dispatchers.IO) {
            fetchUseCase(param).collect { result ->
                _uiState.update { current ->
                    when (result) {
                        is NetworkResult.Loading -> current.copy(isLoading = true, errorMessage = null)
                        is NetworkResult.Success -> current.copy(
                            isLoading = false,
                            data = result.data,
                            errorMessage = null
                        )
                        is NetworkResult.Error -> current.copy(
                            isLoading = false,
                            errorMessage = result.message
                        )
                    }
                }
            }
        }
    }
}
