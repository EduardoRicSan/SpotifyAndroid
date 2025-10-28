package com.tech.domain

import com.tech.core.local.provider.SpotiTokenProvider
import com.tech.core.remote.NetworkResult
import com.tech.data.remote.api.SpotiApiService
import com.tech.data.remote.dto.response.AlbumDTO
import com.tech.data.remote.dto.response.ArtistsDTO
import com.tech.data.remote.dto.response.CategorieItemDTO
import com.tech.data.remote.dto.response.SongDTO
import com.tech.data.remote.dto.response.SpotiTokenResponse
import com.tech.domain.repository.SpotiRepository
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.just
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class SpotiRepositoryTest {

    @MockK
    private lateinit var apiService: SpotiApiService

    @MockK
    private lateinit var tokenProvider: SpotiTokenProvider

    private lateinit var repository: SpotiRepository

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        repository = SpotiRepository(apiService, tokenProvider)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    // ---------------------------------------------------
    // Test: refreshAccessToken()
    // ---------------------------------------------------
    @Test
    fun `refreshAccessToken emits Success when token retrieved`() = runTest {
        val token = "fake_token"
        val response = SpotiTokenResponse(
            accessToken = token,
            expiresIn = 3600,
            tokenType = "Bearer"
        )

        coEvery { apiService.getAccessToken() } returns response
        coEvery { tokenProvider.saveTokenWithExpiry(token, 3600) } just Runs

        val emissions = repository.refreshAccessToken().toList()

        assert(emissions.first() is NetworkResult.Loading)

        val result = emissions.last()
        when (result) {
            is NetworkResult.Success -> assertEquals(token, result.data)
            else -> fail("Expected Success but got $result")
        }
    }

    @Test
    fun `refreshAccessToken emits Error when exception thrown`() = runTest {
        coEvery { apiService.getAccessToken() } throws RuntimeException("Network error")

        val emissions = repository.refreshAccessToken().toList()

        assert(emissions.first() is NetworkResult.Loading)

        val result = emissions.last()
        when (result) {
            is NetworkResult.Error -> assertEquals("Network error", result.message)
            else -> fail("Expected Error but got $result")
        }
    }

    // ---------------------------------------------------
    // Test: getArtists()
    // ---------------------------------------------------
    @Test
    fun `getArtists returns Success`() = runTest {
        val dtoList = listOf(ArtistsDTO(name = "Artist A"))
        coEvery { apiService.getArtists() } returns dtoList

        val emissions = repository.getArtists().toList()
        val result = emissions.last()

        when (result) {
            is NetworkResult.Success -> assertEquals("Artist A", result.data.first().name)
            else -> fail("Expected Success but got $result")
        }
    }

    @Test
    fun `getArtists returns Error`() = runTest {
        coEvery { apiService.getArtists() } throws Exception("Server error")

        val emissions = repository.getArtists().toList()
        val result = emissions.last()

        when (result) {
            is NetworkResult.Error -> assertEquals("Server error", result.message)
            else -> fail("Expected Error but got $result")
        }
    }

    // ---------------------------------------------------
    // Test: getAlbumsByArtist()
    // ---------------------------------------------------
    @Test
    fun `getAlbumsByArtist returns Success`() = runTest {
        val dtoList = listOf(AlbumDTO(id = "album1", name = "Album Title"))
        coEvery { apiService.getAlbumsByArtists("1") } returns dtoList

        val emissions = repository.getAlbumsByArtist("1").toList()
        val result = emissions.last()

        when (result) {
            is NetworkResult.Success -> assertEquals("Album Title", result.data.first().name)
            else -> fail("Expected Success but got $result")
        }
    }

    // ---------------------------------------------------
    // Test: getSongsByAlbum()
    // ---------------------------------------------------
    @Test
    fun `getSongsByAlbum returns Success`() = runTest {
        val dtoList = listOf(SongDTO(id = "song1", name = "Song Title"))
        coEvery { apiService.getSongsByAlbum("album1") } returns dtoList

        val emissions = repository.getSongsByAlbum("album1").toList()
        val result = emissions.last()

        when (result) {
            is NetworkResult.Success -> assertEquals("Song Title", result.data.first().name)
            else -> fail("Expected Success but got $result")
        }
    }

    // ---------------------------------------------------
    // Test: getCategories()
    // ---------------------------------------------------
    @Test
    fun `getCategories returns Success`() = runTest {
        val dtoList = listOf(
            CategorieItemDTO(id = "cat1", name = "Pop"),
            CategorieItemDTO(id = "cat2", name = "Rock")
        )
        coEvery { apiService.getSpotiCategories() } returns dtoList

        val emissions = repository.getCategories().toList()
        val result = emissions.last()

        when (result) {
            is NetworkResult.Success -> assertEquals("Pop", result.data.first().name)
            else -> fail("Expected Success but got $result")
        }
    }
}

