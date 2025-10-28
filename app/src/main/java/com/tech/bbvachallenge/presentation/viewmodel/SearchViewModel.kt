package com.tech.bbvachallenge.presentation.viewmodel

import com.tech.domain.model.CategorieItem
import com.tech.domain.usecase.GetCategoriesByUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getCategoriesByUseCase: GetCategoriesByUseCase
) : BaseViewModel<CategorieItem>(
    fetchUseCase = { getCategoriesByUseCase() }
)