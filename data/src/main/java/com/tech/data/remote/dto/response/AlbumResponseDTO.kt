package com.tech.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlbumResponseDTO(
    @SerialName("total") var total: Int? = null,
    @SerialName("items") var items: List<AlbumDTO> = listOf()
)

@Serializable
data class AlbumDTO(
    @SerialName("total_tracks") var totalTracks: Int? = null,
    @SerialName("id") var id: String? = null,
    @SerialName("images") var images: List<ImagesDTO> = listOf(),
    @SerialName("name") var name: String? = null,
    @SerialName("release_date") var releaseDate: String? = null,
    @SerialName("release_date_precision") var releaseDatePrecision: String? = null,
    @SerialName("uri") var uri: String? = null,
    @SerialName("artists") var artists: List<ArtistsDTO> = listOf(),
)