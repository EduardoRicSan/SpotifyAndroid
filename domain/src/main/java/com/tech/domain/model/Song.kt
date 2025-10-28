package com.tech.domain.model

import com.tech.data.remote.dto.response.SongDTO

data class Song(
    var artists: List<Artist> = listOf(),
    var discNumber: Int? = null,
    var durationMs: Int? = null, var id: String? = null,
    var name: String? = null,
    var trackNumber: Int? = null,
    var type: String? = null,
    var uri: String? = null,
)

// MAPPER
fun SongDTO.toSong() =
    Song(
        artists = artists.map { it.toArtist() },
        discNumber = discNumber,
        durationMs = durationMs,
        name = name,
        trackNumber = trackNumber,
        type = type,
        uri = uri
    )