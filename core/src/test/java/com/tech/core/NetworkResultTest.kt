package com.tech.core

import com.tech.core.remote.NetworkResult
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class NetworkResultTest {

    @Test
    fun `Success should hold correct data`() {
        // Arrange
        val expectedData = "Hello"
        val result = NetworkResult.Success(expectedData)

        // Assert
        assertEquals(expectedData, result.data)
    }

    @Test
    fun `Error should hold correct message and code`() {
        // Arrange
        val message = "Not Found"
        val code = 404
        val result = NetworkResult.Error(message, code)

        // Assert
        assertEquals(message, result.message)
        assertEquals(code, result.code)
    }

    @Test
    fun `Error can be created with only message`() {
        // Arrange
        val message = "Unknown error"
        val result = NetworkResult.Error(message)

        // Assert
        assertEquals(message, result.message)
        assertNull(result.code)
    }

    @Test
    fun `Loading should be of type Loading`() {
        // Act
        val result = NetworkResult.Loading

        // Assert
        assertTrue(result is NetworkResult.Loading)
    }

    @Test
    fun `Different NetworkResult types should not be equal`() {
        // Arrange
        val success = NetworkResult.Success("data")
        val error = NetworkResult.Error("error")
        val loading = NetworkResult.Loading

        // Assert
        assertNotEquals(success, error)
        assertNotEquals(error, loading)
        assertNotEquals(success, loading)
    }

    @Test
    fun `Two Success with same data should be equal`() {
        // Arrange
        val a = NetworkResult.Success("same")
        val b = NetworkResult.Success("same")

        // Assert
        assertEquals(a, b)
    }

    @Test
    fun `Two Error with same message and code should be equal`() {
        // Arrange
        val a = NetworkResult.Error("error", 500)
        val b = NetworkResult.Error("error", 500)

        // Assert
        assertEquals(a, b)
    }
}