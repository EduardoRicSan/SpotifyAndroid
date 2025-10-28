package com.tech.core

import app.cash.turbine.test
import com.tech.core.remote.NetworkResult
import com.tech.core.remote.safeApiCallFlow
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.Response
import java.io.IOException

class SafeApiCallFlowTest {

    @Test
    fun `safeApiCallFlow emits Loading then Success when response is successful`() = runTest {
        // Arrange
        val expectedData = "Success!"
        val mockResponse = Response.success(expectedData)

        // Act
        val flow = safeApiCallFlow { mockResponse }

        // Assert
        flow.test {
            assertTrue(awaitItem() is NetworkResult.Loading) // primer estado
            val success = awaitItem()
            assertTrue(success is NetworkResult.Success)
            assertEquals(expectedData, (success as NetworkResult.Success).data)
            awaitComplete()
        }
    }

    @Test
    fun `safeApiCallFlow emits Loading then Error when response fails`() = runTest {
        // Arrange
        val mockResponse = Response.error<String>(404, "Not Found".toResponseBody())

        // Act
        val flow = safeApiCallFlow { mockResponse }

        // Assert
        flow.test {
            assertTrue(awaitItem() is NetworkResult.Loading)
            val error = awaitItem()
            assertTrue(error is NetworkResult.Error)
            assertEquals(404, (error as NetworkResult.Error).code)
            awaitComplete()
        }
    }

    @Test
    fun `safeApiCallFlow emits Error when throws IOException`() = runTest {
        // Act
        val flow = safeApiCallFlow<String> { throw IOException("Network down") }

        // Assert
        flow.test {
            assertTrue(awaitItem() is NetworkResult.Loading)
            val error = awaitItem()
            assertTrue(error is NetworkResult.Error)
            assertTrue((error as NetworkResult.Error).message!!.contains("Network down"))
            awaitComplete()
        }
    }

    @Test
    fun `safeApiCallFlow emits Error when response body is null`() = runTest {
        // Arrange
        val mockResponse = Response.success<String?>(null)

        // Act
        val flow = safeApiCallFlow { mockResponse }

        // Assert
        flow.test {
            assertTrue(awaitItem() is NetworkResult.Loading)
            val error = awaitItem()
            assertTrue(error is NetworkResult.Error)
            assertEquals("Response body is null", (error as NetworkResult.Error).message)
            awaitComplete()
        }
    }
}