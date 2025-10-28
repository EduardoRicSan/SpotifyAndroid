package com.tech.domain.usecase

import com.tech.core.remote.NetworkResult
import com.tech.domain.model.Song
import com.tech.domain.repository.SpotiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSongsByAlbumUseCase @Inject constructor(
    private val repository: SpotiRepository
) {
    suspend operator fun invoke(albumId: String): Flow<NetworkResult<List<Song>>> {
        return repository.getSongsByAlbum(albumId)
    }
}