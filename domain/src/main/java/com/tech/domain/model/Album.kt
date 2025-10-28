package com.tech.domain.model

import com.tech.data.remote.dto.response.AlbumDTO
import kotlinx.serialization.Serializable


@Serializable
data class Album(
    var totalTracks: Int? = null,
    var id: String? = null,
    var images: List<Images> = emptyList(),
    var name: String? = null,
    var releaseDate: String? = null,
    var releaseDatePrecision: String? = null,
    var uri: String? = null,
    var artists: List<Artist> = emptyList(),
)

//MAPPER
fun AlbumDTO.toAlbum() =
    Album(
        totalTracks = totalTracks,
        id = id,
        images = images.map { it.toImage() },
        name = name,
        releaseDate = releaseDate,
        releaseDatePrecision = releaseDatePrecision,
        uri = uri,
        artists = artists.map { it.toArtist() }
    )