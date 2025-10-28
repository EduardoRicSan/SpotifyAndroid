# Spotify Android App

Una aplicación Android de música que consume la API de Spotify, mostrando canciones, artistas y álbumes. El proyecto está desarrollado siguiendo **Clean Architecture**, **MVVM** y buenas prácticas modernas de desarrollo Android, usando **Jetpack Compose** para la UI, **Room** para persistencia local y **Retrofit/Ktor** para llamadas a APIs REST.

---

## 🔹 Características principales

- Listado de canciones, artistas y álbumes desde la API de Spotify.
- Integración con **API RESTful de Spotify**, manejo de tokens y autenticación.
- Persistencia local usando **Room**, para cachear canciones y mejorar la experiencia offline.
- Navegación y flujos de UI implementados con **Jetpack Compose** y **Navigation Compose**.
- Modularización y arquitectura limpia:
  - `app`: módulo principal.
  - `core`: utilidades, network, y componentes compartidos.
  - `data`: repositorios, fuentes de datos, DTOs y mappers.
  - `domain`: use cases y entidades del negocio.
  - `presentation`: pantallas, ViewModels y UI.
- Manejo de errores y resultados de red con `NetworkResult` y `safeApiCall`.
- Pruebas unitarias con **JUnit** y **Mockito**.
- Pruebas de UI con **Espresso**.
- Integración de **Jetpack Compose** con soporte a temas claros y oscuros.
- Compatible con **Android 7+ (API 21)**.

---

## 🔹 Estructura del proyecto


- **data**: Contiene repositorios, API services, DTOs, Room, mappers y lógica de persistencia.
- **domain**: Contiene entidades y casos de uso (`UseCases`) que representan la lógica de negocio.
- **presentation**: Contiene pantallas Compose, ViewModels y navegación.
- **core**: Contiene utilidades, constantes, network helpers y componentes de UI reutilizables.
- **app**: Módulo que integra todo y contiene la configuración de Hilt, navegación y temas.

---

## 🔹 Tecnologías y librerías

- Kotlin y Kotlin Coroutines
- Jetpack Compose
- Navigation Compose
- Ktor
- Room
- DataStore / SharedPreferences
- Dagger Hilt
- Kotlin Serialization 
- JUnit, MockK
- Espresso
- AndroidX Core SplashScreen

---

## 🔹 Configuración del proyecto

1. Clona el repositorio:

```bash
git clone https://github.com/tu-usuario/spotify-android-app.git
cd spotify-android-app


