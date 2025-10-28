package com.tech.data

import com.tech.data.remote.api.SpotiApiConstants
import com.tech.data.remote.api.SpotiApiServiceImpl
import com.tech.data.remote.dto.response.AlbumDTO
import com.tech.data.remote.dto.response.AlbumResponseDTO
import com.tech.data.remote.dto.response.ArtistsDTO
import com.tech.data.remote.dto.response.ArtistsResponseDTO
import com.tech.data.remote.dto.response.CategorieDTO
import com.tech.data.remote.dto.response.CategorieItemDTO
import com.tech.data.remote.dto.response.CategoriesResponseDTO
import com.tech.data.remote.dto.response.SongDTO
import com.tech.data.remote.dto.response.SongResponseDTO
import com.tech.data.remote.dto.response.SpotiTokenResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondError
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.fullPath
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class SpotiApiServiceImplTest {

    private val json = Json { ignoreUnknownKeys = true; prettyPrint = true }

    @Test
    fun `test getArtists returns list of artists`() = runBlocking {
        val mockEngine = MockEngine { request ->
            val path = request.url.fullPath
            println("👉 Request path: $path")

            when {
                path.contains(SpotiApiConstants.GET_ARTISTS_ENDPOINT) -> {
                    respond(
                        json.encodeToString(
                            ArtistsResponseDTO(
                                artists = listOf(ArtistsDTO(id = "1", name = "Artist1"))
                            )
                        ),
                        HttpStatusCode.OK,
                        headersOf(HttpHeaders.ContentType, "application/json")
                    )
                }

                else -> respondError(HttpStatusCode.NotFound)
            }
        }

        val client = HttpClient(mockEngine) {
            install(ContentNegotiation) { json(json) }
        }

        val service = SpotiApiServiceImpl(spotiClient = client, accountClient = client)

        val result = service.getArtists()

        assertTrue(result.isNotEmpty())
        assertEquals("Artist1", result.first().name)
    }

    @Test
    fun `test getAlbumsByArtists returns list of albums`() = runBlocking {
        val mockEngine = MockEngine { request ->
            val path = request.url.fullPath
            println("👉 Request path: $path")

            when {
                path.contains("/v1/artists/1/albums") -> {
                    respond(
                        json.encodeToString(
                            AlbumResponseDTO(
                                items = listOf(AlbumDTO(id = "album1", name = "Album1"))
                            )
                        ),
                        HttpStatusCode.OK,
                        headersOf(HttpHeaders.ContentType, "application/json")
                    )
                }

                else -> respondError(HttpStatusCode.NotFound)
            }
        }

        val client = HttpClient(mockEngine) {
            install(ContentNegotiation) { json(json) }
        }

        val service = SpotiApiServiceImpl(spotiClient = client, accountClient = client)

        val result = service.getAlbumsByArtists("1")

        assertTrue(result.isNotEmpty())
        assertEquals("Album1", result.first().name)
    }

    @Test
    fun `test getSongsByAlbum returns list of songs`() = runBlocking {
        val mockEngine = MockEngine { request ->
            val path = request.url.fullPath
            println("👉 Request path: $path")

            when {
                path.contains("/v1/albums/album1/tracks") -> {
                    respond(
                        json.encodeToString(
                            SongResponseDTO(
                                items = listOf(SongDTO(id = "song1", name = "Song1"))
                            )
                        ),
                        HttpStatusCode.OK,
                        headersOf(HttpHeaders.ContentType, "application/json")
                    )
                }

                else -> respondError(HttpStatusCode.NotFound)
            }
        }

        val client = HttpClient(mockEngine) {
            install(ContentNegotiation) { json(json) }
        }

        val service = SpotiApiServiceImpl(spotiClient = client, accountClient = client)

        val result = service.getSongsByAlbum("album1")

        assertTrue(result.isNotEmpty())
        assertEquals("Song1", result.first().name)
    }

    @Test
    fun `test getSpotiCategories returns list of categories`() = runBlocking {
        val mockEngine = MockEngine { request ->
            val path = request.url.fullPath
            println("👉 Request path: $path")

            when {
                path.contains(SpotiApiConstants.GET_SPOTI_CATEGORIES) -> {
                    respond(
                        json.encodeToString(
                            CategoriesResponseDTO(
                                categories = CategorieDTO(
                                    items = listOf(CategorieItemDTO(id = "cat1", name = "Pop"))
                                )
                            )
                        ),
                        HttpStatusCode.OK,
                        headersOf(HttpHeaders.ContentType, "application/json")
                    )
                }

                else -> respondError(HttpStatusCode.NotFound)
            }
        }

        val client = HttpClient(mockEngine) {
            install(ContentNegotiation) { json(json) }
        }

        val service = SpotiApiServiceImpl(spotiClient = client, accountClient = client)

        val result = service.getSpotiCategories()

        assertTrue(result.isNotEmpty())
        assertEquals("Pop", result.first().name)
    }
}