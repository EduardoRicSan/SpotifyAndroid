package com.tech.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtistsResponseDTO(
    @SerialName("artists") var artists : List<ArtistsDTO> = listOf()
)

@Serializable
data class ArtistsDTO(
    @SerialName("followers") var followers: FollowersDTO? = FollowersDTO(),
    @SerialName("genres") var genres: List<String> = listOf(),
    @SerialName("id") var id: String? = null,
    @SerialName("images") var images: List<ImagesDTO> = listOf(),
    @SerialName("name") var name: String? = null,
    @SerialName("popularity") var popularity: Int? = null,
)

@Serializable
data class FollowersDTO(
    @SerialName("href") var href: String? = null,
    @SerialName("total") var total: Int? = null

)

@Serializable
data class ImagesDTO(
    @SerialName("url") var url: String? = null,
    @SerialName("height") var height: Int? = null,
    @SerialName("width") var width: Int? = null

)