package com.tech.data.remote.api

object SpotiApiConstants {
    const val BASE_URL = "https://api.spotify.com/"
    const val GET_ARTISTS_ENDPOINT = "v1/artists"
    const val GET_ALBUM_BY_ARTIST_ENDPOINT  = "v1/artists/"
    const val GET_SONG_BY_ALBUM_ENDPOINT  = "v1/albums/"

    const val GET_SPOTI_CATEGORIES = "v1/browse/categories"
    const val BODY_TOKEN_FIRST_PART = "grant_type=client_credentials&client_id="
    const val BODY_TOKEN_SECOND_PART = "client_secret="

    const val SPOTI_ACCOUNT_BASE_URL = "https://accounts.spotify.com/api/"
    const val TOKEN_ENDPOINT = "token"


    const val CLIENT_ID="b71d1b56c36e4ebc95fcce0babffca61"
    const val CLIENT_SECRET="220180ab00364c07bfb9429fb7d5501b"
}

object SpotiArtist {
    const val ID_QUEEN = "1dfeR4HaWDbWqFHLkxsg1d"
    const val ID_ELEFANTE = "5oYHL2SijkMY52gKIhYJNb"
    const val ID_ISKANDER = "6qEHX4yRVZ5WP069VAaC2p"
    const val ID_REILY_BARBA = "69BUYvpG9MbjCyIZfsFdhJ"
    const val ID_CODA = "3qX79XCeQcRJdmlrcZZVIy"
    const val ID_THE_WEEKND = "1Xyo4u8uXC1ZmMpatF05PJ"
    const val ID_BRUNO_MARS = "0du5cEVh5yTK9QJze8zA0C"
    const val ID_GRUPO_FRONTERA = "6XkjpgcEsYab502Vr1bBeW"
    const val ID_JOSEJOSE = "4mN0qcMxWX8oToqfDPM5yV"
    const val ID_BAD_BUNNY = "4q3ewBCX7sLwd24euuV69X"
    const val ID_BON_JOVI = "58lV9VcRSjABbAbfWS6skp"
    const val ID_MELENDI = "1EXjXQpDx2pROygh8zvHs4"
    const val ID_GRUPO_FIRME = "1dKdetem2xEmjgvyymzytS"
    const val ID_DADDY_YANKEE = "4VMYDCV2IEDYJArk749S6m"
    const val ID_THE_CLAPTON = "4mncDFjVLUa3s025Tct3Ry"
    const val ID_LUIS_MIGUEL = "2nszmSgqreHSdJA3zWPyrW"
    const val ID_ZHU = "28j8lBWDdDSHSSt5oPlsX2"
    const val ID_MICHAEL_JACKSON = "3fMbdgg4jU18AjLCKBhRSm"
    const val ID_MANESKIN = "0lAWpj5szCSwM4rUMHYmrr"
    const val ID_PABLO_ALBORAN = "5M9Bb4adKAgrOFOhc05Y50"
    const val ID_DEADMAU5 = "2CIMQHirSU0MQqyYHq0eOx"
    const val ID_MIKE_TOWERS = "7iK8PXO48WeuP03g8YR51W"
    const val ARTISTS_REQUEST_PARAM = "ids"
    val SPOTI_ARTISID_LIST = listOf(
        ID_QUEEN,
        ID_ELEFANTE,
        ID_ISKANDER,
        ID_REILY_BARBA,
        ID_CODA,
        ID_THE_WEEKND,
        ID_BRUNO_MARS,
        ID_GRUPO_FRONTERA,
        ID_JOSEJOSE,
        ID_BAD_BUNNY,
        ID_BON_JOVI,
        ID_MELENDI,
        ID_GRUPO_FIRME,
        ID_DADDY_YANKEE,
        ID_THE_CLAPTON,
        ID_LUIS_MIGUEL,
        ID_ZHU,
        ID_MICHAEL_JACKSON,
        ID_MANESKIN,
        ID_PABLO_ALBORAN,
        ID_DEADMAU5,
        ID_MIKE_TOWERS
    )
}