package com.tech.domain.usecase

import com.tech.core.remote.NetworkResult
import com.tech.domain.model.CategorieItem
import com.tech.domain.repository.SpotiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesByUseCase @Inject constructor(
    private val repository: SpotiRepository
) {
    suspend operator fun invoke(): Flow<NetworkResult<List<CategorieItem>>> {
        return repository.getCategories()
    }
}