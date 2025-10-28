package com.tech.domain.usecase

import com.tech.core.remote.NetworkResult
import com.tech.domain.repository.SpotiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RefreshTokenUseCase @Inject constructor(
    private val repository: SpotiRepository
) {
    operator fun invoke(): Flow<NetworkResult<String>> {
        return repository.refreshAccessToken()
    }
}