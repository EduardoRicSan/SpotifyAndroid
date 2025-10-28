package com.tech.design_system.common.model

/**
 * Clase que representa un mensaje para un TopSnackbar
 *
 * @param message Texto principal del snackbar
 * @param actionLabel Texto opcional para la acción del snackbar
 * @param durationMillis Duración del snackbar (Corta, Media, Indefinida)
 * @param onAction Lambda opcional a ejecutar si se pulsa la acción
 */
data class SpotiSnackbarMessage(
    val message: String,
    val type: SpotiSnackbarType = SpotiSnackbarType.Info,
    val durationMillis: Long = 5000L,
    val actionLabel: String? = null,
    val onAction: (() -> Unit)? = null
)

enum class SpotiSnackbarType {
    Success, Error, Warning, Info
}
