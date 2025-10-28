package com.tech.bbvachallenge.presentation.demos

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.tech.design_system.common.model.SpotiPromoItem
import com.tech.design_system.components.carousel.SpotiPromoCarousel

/**
 * This section is just to fill Demos in the feed section
 */

@Composable
fun DemoSpotiPromoCarousel() {
    val promos = listOf(
        SpotiPromoItem(
            title = "Coldplay Tour 2025",
            description = "Vive la nueva gira mundial ‘Moon Music’.",
            buttonText = "Ver fechas",
            startColor = Color(0xFF6A11CB),
            endColor = Color(0xFF2575FC),
            imageUrl = "https://indierocks.sfo3.cdn.digitaloceanspaces.com/wp-content/uploads/bfi_thumb/coldplay-2-b8w4vgz01il4k6ih6o3l0mn475ioizdioo9gdd58xv7abteu3hz38eewan0vsw.jpg"
        ),
        SpotiPromoItem(
            title = "Billie Eilish - Hit Me Hard and Soft",
            description = "Nuevo álbum ya disponible.",
            buttonText = "Escuchar ahora",
            startColor = Color(0xFF000000),
            endColor = Color(0xFF434343),
            imageUrl = "https://www.totalisimo.com/wp-content/uploads/2024/08/billie-eilish-sconvierte-artista-mas-escuchada-mundo.jpeg"
        ),
        SpotiPromoItem(
            title = "The Weeknd - After Hours Til Dawn",
            description = "Una experiencia visual y sonora sin igual.",
            buttonText = "Ver detalles",
            startColor = Color(0xFF93291E),
            endColor = Color(0xFFED213A),
            imageUrl = "https://i.pinimg.com/736x/58/ec/64/58ec6444767678f10c4d16fed41b5b44.jpg"
        ),
        SpotiPromoItem(
            title = "Tomorrowland 2025",
            description = "El festival más grande del planeta te espera.",
            buttonText = "Compra tus boletos",
            startColor = Color(0xFFFF512F),
            endColor = Color(0xFFF09819),
            imageUrl = "https://ik.imagekit.io/etransfers/blog/etransfers/Tomorrowland_Tulum_2023/f850x638-12153_89642_489_bOyh0Is76.jpg?ik-sdk-version=javascript-1.4.3&updatedAt=1671304215791"
        ),
        SpotiPromoItem(
            title = "Dua Lipa - Radical Optimism",
            description = "Descubre su nuevo sonido lleno de energía.",
            buttonText = "Escuchar álbum",
            startColor = Color(0xFFFC5C7D),
            endColor = Color(0xFF6A82FB),
            imageUrl = "https://m.media-amazon.com/images/I/71AyfVKzxLL._UF1000,1000_QL80_.jpg"
        ),
        SpotiPromoItem(
            title = "Arctic Monkeys en vivo",
            description = "Revive los clásicos de ‘AM’ y más.",
            buttonText = "Ver fechas",
            startColor = Color(0xFF232526),
            endColor = Color(0xFF414345),
            imageUrl = "https://akamai.sscdn.co/gcs/cifra-blog/es/wp-content/uploads/2023/03/a0ed1de-Arctic-Monkeys-1024x576.jpeg"
        ),
        SpotiPromoItem(
            title = "Coachella 2025",
            description = "Más de 100 artistas, 3 días, 1 experiencia inolvidable.",
            buttonText = "Explorar lineup",
            startColor = Color(0xFFFF9966),
            endColor = Color(0xFFFF5E62),
            imageUrl = "https://e01-phantom-marca-mx.uecdn.es/c1ec5a7f4e933fc3fc29a5a36f1103fc/resize/1200/f/webp/mx/assets/multimedia/imagenes/2025/04/03/17436998932456.jpg"
        ),
        SpotiPromoItem(
            title = "Adele - Las Vegas Residency",
            description = "Una voz, un piano y una noche inolvidable.",
            buttonText = "Reservar entradas",
            startColor = Color(0xFF485563),
            endColor = Color(0xFF29323C),
            imageUrl = "https://www.mondosonoro.com/wp-content/uploads/2020/02/adele-press.jpg"
        )
    )

    // Usa tu carrusel promocional con autoscroll
    SpotiPromoCarousel(
        SpotiPromoItems = promos,
        autoScrollDelay = 4000L
    )
}