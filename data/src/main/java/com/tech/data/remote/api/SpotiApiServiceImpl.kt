package com.tech.data.remote.api

import com.tech.data.remote.api.SpotiArtist.ARTISTS_REQUEST_PARAM
import com.tech.data.remote.dto.response.ArtistsDTO
import com.tech.data.remote.dto.response.ArtistsResponseDTO
import com.tech.data.remote.dto.response.AlbumDTO
import com.tech.data.remote.dto.response.AlbumResponseDTO
import com.tech.data.remote.dto.response.CategorieDTO
import com.tech.data.remote.dto.response.CategorieItemDTO
import com.tech.data.remote.dto.response.CategoriesResponseDTO
import com.tech.data.remote.dto.response.SongDTO
import com.tech.data.remote.dto.response.SongResponseDTO
import com.tech.data.remote.dto.response.SpotiTokenResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class SpotiApiServiceImpl(
    private val spotiClient: HttpClient,
    private val accountClient: HttpClient
) : SpotiApiService {

    override suspend fun getAccessToken(
    ): SpotiTokenResponse {
        val body = "${SpotiApiConstants.BODY_TOKEN_FIRST_PART}${SpotiApiConstants.CLIENT_ID}&${SpotiApiConstants.BODY_TOKEN_SECOND_PART}${SpotiApiConstants.CLIENT_SECRET}"
        return accountClient.post(SpotiApiConstants.TOKEN_ENDPOINT) {
            contentType(ContentType.Application.FormUrlEncoded)
            setBody(body)
        }.body()
    }


    override suspend fun getArtists(): List<ArtistsDTO> {
       val ids = SpotiArtist.SPOTI_ARTISID_LIST.joinToString(",") { it.trim() }
        val response: ArtistsResponseDTO =
            spotiClient.get(SpotiApiConstants.GET_ARTISTS_ENDPOINT) {
                parameter(ARTISTS_REQUEST_PARAM, ids)
            }.body()
        return response.artists
    }

    override suspend fun getAlbumsByArtists(artistID: String): List<AlbumDTO> {
        val response: AlbumResponseDTO =
            spotiClient.get("${SpotiApiConstants.GET_ALBUM_BY_ARTIST_ENDPOINT}$artistID/albums").body()
        return response.items
    }


    override suspend fun getSongsByAlbum(albumID: String): List<SongDTO> {
        val response: SongResponseDTO =
            spotiClient.get("${SpotiApiConstants.GET_SONG_BY_ALBUM_ENDPOINT}$albumID/tracks").body()
        return response.items
    }

    override suspend fun getSpotiCategories(): List<CategorieItemDTO> {
        val response: CategoriesResponseDTO =
            spotiClient.get(SpotiApiConstants.GET_SPOTI_CATEGORIES).body()
        return response.categories?.items ?: emptyList()
    }

}