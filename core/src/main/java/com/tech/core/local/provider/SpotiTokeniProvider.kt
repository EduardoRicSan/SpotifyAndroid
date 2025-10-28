package com.tech.core.local.provider

import com.tech.core.local.datastore.SpotiDataStore
import com.tech.core.remote.NetworkResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SpotiTokenProvider @Inject constructor(
    private val dataStore: SpotiDataStore
) {
    private val _token = MutableStateFlow("")
    val token: StateFlow<String> = _token

    val expiresAt: Flow<Long> = dataStore.getSpotifyExpiresAtFlow()

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    init {
        scope.launch {
            dataStore.getStringFlow("spoti_access_token").collect { savedToken ->
                _token.value = savedToken
            }
        }
    }

    suspend fun saveToken(newToken: String) {
        dataStore.saveString("spoti_access_token", newToken)
        _token.value = newToken
    }

    suspend fun saveTokenWithExpiry(token: String, expiresInSeconds: Long) {
        dataStore.saveSpotifyToken(token, expiresInSeconds)
        _token.value = token
    }

}