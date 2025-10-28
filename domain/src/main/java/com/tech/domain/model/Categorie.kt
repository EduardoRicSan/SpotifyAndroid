package com.tech.domain.model

import com.tech.data.remote.dto.response.CategorieItemDTO
import com.tech.data.remote.dto.response.IconsDTO
import kotlin.String


data class CategorieItem(
    var href: String? = null,
    var id: String? = null,
    var icons: List<Icons> = listOf(),
    var name: String? = null
)
data class Icons(
    var height: Int? = null,
    var url: String? = null,
    var width: Int? = null
)

// MAPPER
fun CategorieItemDTO.toCategorieItem() =
    CategorieItem(
         href = href,
        id = id,
        icons = icons.map { it.toIcon() },
        name = name
    )

fun IconsDTO.toIcon() =
    Icons(
        height = height,
        url = url,
        width = width
    )