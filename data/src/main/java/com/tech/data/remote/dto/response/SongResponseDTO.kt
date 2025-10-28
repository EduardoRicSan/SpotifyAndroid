package com.tech.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SongResponseDTO(
    @SerialName("items") var items: List<SongDTO> = listOf(),
    @SerialName("total") var total: Int? = null
)

@Serializable
data class SongDTO(
    @SerialName("artists") var artists: List<ArtistsDTO> = listOf(),
    @SerialName("disc_number") var discNumber: Int? = null,
    @SerialName("duration_ms") var durationMs: Int? = null,
    @SerialName("id") var id: String? = null,
    @SerialName("name") var name: String? = null,
    @SerialName("track_number") var trackNumber: Int? = null,
    @SerialName("type") var type: String? = null,
    @SerialName("uri") var uri: String? = null,
)
