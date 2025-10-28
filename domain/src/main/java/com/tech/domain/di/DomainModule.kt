package com.tech.domain.di

import com.tech.core.local.provider.SpotiTokenProvider
import com.tech.data.remote.api.SpotiApiService
import com.tech.domain.repository.SpotiRepository
import com.tech.domain.usecase.GetAlbumsByArtistUseCase
import com.tech.domain.usecase.GetArtistUseCase
import com.tech.domain.usecase.RefreshTokenUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideRefreshTokenUseCase(
        spotiRepository: SpotiRepository
    ): RefreshTokenUseCase = RefreshTokenUseCase(spotiRepository)


    @Provides
    @Singleton
    fun provideGetArtistUseCase(
        spotiRepository: SpotiRepository
    ): GetArtistUseCase = GetArtistUseCase(spotiRepository)

    @Provides
    @Singleton
    fun provideGetAlbumsByArtistUseCase(
        spotiRepository: SpotiRepository
    ): GetAlbumsByArtistUseCase = GetAlbumsByArtistUseCase(spotiRepository)

    @Provides
    @Singleton
    fun provideSpotiRepository(
        apiService: SpotiApiService,
        tokenProvider: SpotiTokenProvider
    ): SpotiRepository = SpotiRepository(
        apiService, tokenProvider
    )

}