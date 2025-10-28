package com.tech.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoriesResponseDTO(
    @SerialName("categories") var categories: CategorieDTO? = CategorieDTO()
)
@Serializable
data class CategorieDTO(
    @SerialName("href") var href: String? = null,
    @SerialName("items") var items: List<CategorieItemDTO> = listOf(),
    @SerialName("limit") var limit: Int? = null,
    @SerialName("next") var next: String? = null,
    @SerialName("offset") var offset: Int? = null,
    @SerialName("previous") var previous: String? = null,
    @SerialName("total") var total: Int? = null
)

@Serializable
data class CategorieItemDTO(
    @SerialName("href") var href: String? = null,
    @SerialName("id") var id: String? = null,
    @SerialName("icons") var icons: List<IconsDTO> = listOf(),
    @SerialName("name") var name: String? = null
)

@Serializable
data class IconsDTO(
    @SerialName("height") var height: Int? = null,
    @SerialName("url") var url: String? = null,
    @SerialName("width") var width: Int? = null

)