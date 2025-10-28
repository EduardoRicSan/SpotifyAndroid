package com.tech.domain.usecase

import com.tech.core.remote.NetworkResult
import com.tech.domain.model.Album
import com.tech.domain.repository.SpotiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAlbumsByArtistUseCase @Inject constructor(
    private val repository: SpotiRepository
) {
    suspend operator fun invoke(artistID: String): Flow<NetworkResult<List<Album>>> {
        return repository.getAlbumsByArtist(artistID)
    }
}