package com.tech.domain.model

import com.tech.data.remote.dto.response.ArtistsDTO
import com.tech.data.remote.dto.response.FollowersDTO
import com.tech.data.remote.dto.response.ImagesDTO
import kotlinx.serialization.Serializable


@Serializable
data class Artist(
    var followers: Followers? = Followers(),
    var genres: List<String> = listOf(),
    var id: String? = null,
    var images: List<Images> = listOf(),
    var name: String? = null,
    var popularity: Int? = null,
)

@Serializable
data class Followers(
    var href: String? = null,
    var total: Int? = null
)

@Serializable
data class Images(
    var url: String? = null,
    var height: Int? = null,
    var width: Int? = null
)


// ARTIST MAPPERS
fun ArtistsDTO.toArtist() =
    Artist(
        followers = followers?.toFollower(),
        genres = genres,
        id = id,
        images = images.map { it.toImage() },
        name = name,
        popularity = popularity
    )

fun FollowersDTO.toFollower() =
    Followers(
        href = href,
        total = total
    )

fun ImagesDTO.toImage() =
    Images(
        url = url,
        height = height,
        width = width
    )