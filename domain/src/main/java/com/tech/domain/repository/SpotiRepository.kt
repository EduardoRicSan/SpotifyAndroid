package com.tech.domain.repository

import com.tech.core.local.provider.SpotiTokenProvider
import com.tech.core.remote.NetworkResult
import com.tech.core.remote.safeApiCall
import com.tech.data.remote.api.SpotiApiService
import com.tech.domain.model.Album
import com.tech.domain.model.Artist
import com.tech.domain.model.CategorieItem
import com.tech.domain.model.Song
import com.tech.domain.model.toAlbum
import com.tech.domain.model.toArtist
import com.tech.domain.model.toCategorieItem
import com.tech.domain.model.toSong
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SpotiRepository @Inject constructor(
    private val spotiApiService: SpotiApiService,
    private val tokenProvider: SpotiTokenProvider
)  {

    /**
    * Refresh Spotify access token and save in DataStore
    */
    fun refreshAccessToken(): Flow<NetworkResult<String>> = flow {
        emit(NetworkResult.Loading)
        try {
            val response = spotiApiService.getAccessToken()
            tokenProvider.saveTokenWithExpiry(response.accessToken, response.expiresIn)
            emit(NetworkResult.Success(response.accessToken))
        } catch (e: Exception) {
            emit(NetworkResult.Error(e.message ?: "Unknown Error"))
        }
    }.flowOn(Dispatchers.IO)

    /**
     * Get Artists by ID pre-loaded list
     */
    suspend fun getArtists(): Flow<NetworkResult<List<Artist>>> = safeApiCall {
        spotiApiService.getArtists().map { it.toArtist() }
    }.flowOn(Dispatchers.IO)

    /**
     * Get Albums by Artis
     */
    suspend fun getAlbumsByArtist(artistID: String): Flow<NetworkResult<List<Album>>> = safeApiCall {
        spotiApiService.getAlbumsByArtists(artistID).map { it.toAlbum() }
    }.flowOn(Dispatchers.IO)

    /**
     * Get Songs by Album Id
     */
    suspend fun getSongsByAlbum(albumId: String): Flow<NetworkResult<List<Song>>> = safeApiCall {
        spotiApiService.getSongsByAlbum(albumId).map { it.toSong() }
    }.flowOn(Dispatchers.IO)

    /**
     * Get Categories
     */
    suspend fun getCategories(): Flow<NetworkResult<List<CategorieItem>>> = safeApiCall {
        spotiApiService.getSpotiCategories().map { it.toCategorieItem() }
    }.flowOn(Dispatchers.IO)

}