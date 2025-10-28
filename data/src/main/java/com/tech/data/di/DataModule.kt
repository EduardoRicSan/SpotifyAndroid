package com.tech.data.di

import android.util.Log
import com.tech.core.local.provider.SpotiTokenProvider
import com.tech.data.remote.api.SpotiApiConstants
import com.tech.data.remote.api.SpotiApiService
import com.tech.data.remote.api.SpotiApiServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    @SpotiAccountClient
    fun provideAccountHttpClient(): HttpClient = HttpClient(Android) {
        install(Logging) {
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                }
            )
        }
        defaultRequest {
            url(SpotiApiConstants.SPOTI_ACCOUNT_BASE_URL)
            url {
                protocol = URLProtocol.HTTPS
            }
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
    }

    @Provides
    @Singleton
    @SpotiClient
    fun provideSpotifyHttpClient(
        tokenProvider: SpotiTokenProvider
    ): HttpClient = HttpClient(Android) {

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.d("SpotifyClient", message)
                }
            }
            level = LogLevel.ALL
        }

        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                }
            )
        }

        install(HttpTimeout) {
            connectTimeoutMillis = 120_000
            requestTimeoutMillis = 120_000
            socketTimeoutMillis = 120_000
        }

        defaultRequest {
            url(SpotiApiConstants.BASE_URL)
            url {
                protocol = URLProtocol.HTTPS
            }
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)

            // This block is suspend, so we can safely call Flow.first()
            runBlocking {
                val token = tokenProvider.token.first()
                if (token.isNotEmpty()) {
                    header("Authorization", "Bearer $token")
                }
            }
        }

    }

    @Provides
    @Singleton
    fun provideTickerApiService(
        @SpotiClient spotiHttpClient: HttpClient,
        @SpotiAccountClient accountClient: HttpClient,
    ): SpotiApiService =
        SpotiApiServiceImpl(spotiHttpClient, accountClient)



}