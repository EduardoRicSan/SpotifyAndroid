package com.tech.data.remote.api

import com.tech.data.remote.dto.response.AlbumDTO
import com.tech.data.remote.dto.response.ArtistsDTO
import com.tech.data.remote.dto.response.CategorieDTO
import com.tech.data.remote.dto.response.CategorieItemDTO
import com.tech.data.remote.dto.response.CategoriesResponseDTO
import com.tech.data.remote.dto.response.SongDTO
import com.tech.data.remote.dto.response.SpotiTokenResponse

interface SpotiApiService  {
    suspend fun getAccessToken(
    ): SpotiTokenResponse
    suspend fun getArtists(): List<ArtistsDTO>
    suspend fun getAlbumsByArtists(artistID: String): List<AlbumDTO>
    suspend fun getSongsByAlbum(albumID: String): List<SongDTO>

    suspend fun getSpotiCategories(): List<CategorieItemDTO>
}