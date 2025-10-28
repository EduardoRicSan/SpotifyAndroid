package com.tech.core.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import javax.inject.Inject

/**
 * DataStore helper to store different types of data, including the Spotify access token.
 */
class SpotiDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    companion object {
        val FIRST_LAUNCH_KEY = booleanPreferencesKey("first_launch")
         val SPOTI_ACCESS_TOKEN = stringPreferencesKey("spoti_access_token")
         val SPOTI_EXPIRES_AT = longPreferencesKey("spoti_expires_at")
    }


    suspend fun saveSpotifyToken(token: String, expiresInSeconds: Long) {
        val expiresAt = System.currentTimeMillis() + expiresInSeconds * 1000
        dataStore.edit { prefs ->
            prefs[SPOTI_ACCESS_TOKEN] = token
            prefs[SPOTI_EXPIRES_AT] = expiresAt
        }
    }

    fun getSpotifyTokenFlow(): Flow<String?> =
        dataStore.data.map { it[SPOTI_ACCESS_TOKEN] }

    fun getSpotifyExpiresAtFlow(): Flow<Long> =
        dataStore.data.map { it[SPOTI_EXPIRES_AT] ?: 0L }

    suspend fun clearSpotifyToken() {
        dataStore.edit { prefs ->
            prefs.remove(SPOTI_ACCESS_TOKEN)
            prefs.remove(SPOTI_EXPIRES_AT)
        }
    }

    // -------------------- Boolean --------------------
    fun isFirstLaunch(): Flow<Boolean> {
        val prefKey = booleanPreferencesKey(FIRST_LAUNCH_KEY.name)
        return dataStore.data.map { it[prefKey] ?: true }
    }

    suspend fun disableFirstLaunch() {
        val prefKey = booleanPreferencesKey(FIRST_LAUNCH_KEY.name)
        dataStore.edit { it[prefKey] = false }
    }

    suspend fun saveBoolean(key: String, value: Boolean) {
        val prefKey = booleanPreferencesKey(key)
        dataStore.edit { it[prefKey] = value }
    }

    fun getBooleanFlow(key: String): Flow<Boolean> {
        val prefKey = booleanPreferencesKey(key)
        return dataStore.data.map { it[prefKey] ?: false }
    }

    // -------------------- String --------------------
    suspend fun saveString(key: String, value: String) {
        val prefKey = stringPreferencesKey(key)
        dataStore.edit { it[prefKey] = value }
    }

    fun getStringFlow(key: String): Flow<String> {
        val prefKey = stringPreferencesKey(key)
        return dataStore.data.map { it[prefKey] ?: "" }
    }

    // -------------------- Int --------------------
    suspend fun saveInt(key: String, value: Int) {
        val prefKey = intPreferencesKey(key)
        dataStore.edit { it[prefKey] = value }
    }

    fun getIntFlow(key: String, default: Int = 0): Flow<Int> {
        val prefKey = intPreferencesKey(key)
        return dataStore.data.map { it[prefKey] ?: default }
    }

    // -------------------- Long --------------------
    suspend fun saveLong(key: String, value: Long) {
        val prefKey = longPreferencesKey(key)
        dataStore.edit { it[prefKey] = value }
    }

    fun getLongFlow(key: String, default: Long = 0L): Flow<Long> {
        val prefKey = longPreferencesKey(key)
        return dataStore.data.map { it[prefKey] ?: default }
    }

    // -------------------- Double --------------------
    suspend fun saveDouble(key: String, value: Double) {
        val prefKey = doublePreferencesKey(key)
        dataStore.edit { it[prefKey] = value }
    }

    fun getDoubleFlow(key: String, default: Double = 0.0): Flow<Double> {
        val prefKey = doublePreferencesKey(key)
        return dataStore.data.map { it[prefKey] ?: default }
    }

    // -------------------- Float --------------------
    suspend fun saveFloat(key: String, value: Float) {
        val prefKey = floatPreferencesKey(key)
        dataStore.edit { it[prefKey] = value }
    }

    fun getFloatFlow(key: String): Flow<Float> {
        val prefKey = floatPreferencesKey(key)
        return dataStore.data.map { it[prefKey] ?: 0f }
    }

    // -------------------- String List --------------------
    suspend fun saveStringList(key: String, value: List<String>) {
        val prefKey = stringPreferencesKey(key)
        val json = kotlinx.serialization.json.Json.encodeToString(value)
        dataStore.edit { it[prefKey] = json }
    }

    fun getStringListFlow(key: String): Flow<List<String>> {
        val prefKey = stringPreferencesKey(key)
        return dataStore.data
            .map { prefs ->
                prefs[prefKey]?.let {
                    try {
                        kotlinx.serialization.json.Json.decodeFromString<List<String>>(it)
                    } catch (e: Exception) {
                        emptyList()
                    }
                } ?: emptyList()
            }
            .distinctUntilChanged()
    }
}