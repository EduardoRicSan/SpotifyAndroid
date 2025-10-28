package com.tech.domain.usecase

import com.tech.core.remote.NetworkResult
import com.tech.domain.model.Artist
import com.tech.domain.repository.SpotiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArtistUseCase @Inject constructor(
    private val repository: SpotiRepository
) {
    suspend operator fun invoke(): Flow<NetworkResult<List<Artist>>> {
        return repository.getArtists()
    }
}