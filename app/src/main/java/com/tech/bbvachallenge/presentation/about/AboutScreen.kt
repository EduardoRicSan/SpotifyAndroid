package com.tech.bbvachallenge.presentation.about

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tech.bbvachallenge.R
import com.tech.design_system.common.model.SpotiImageSource
import com.tech.design_system.components.card.SpotiCircularProfileCard
import com.tech.design_system.components.image.SpotiCircularImage
import com.tech.design_system.components.text.SpotiBodyText
import com.tech.design_system.components.text.SpotiHeadlineText
import com.tech.design_system.components.text.SpotiSubtitleText

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    onEditClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagen circular del perfil
        SpotiCircularImage(
            source = SpotiImageSource.Url("https://media.licdn.com/dms/image/v2/C4E03AQGrahdPFsBXRA/profile-displayphoto-shrink_200_200/profile-displayphoto-shrink_200_200/0/1629826477560?e=2147483647&v=beta&t=L_fWqCd9kEH3qM5tcuUZqAybyANPsM5fysqbUwNNIfw"),
            size = 120.dp,
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Nombre principal
        SpotiHeadlineText(
            text = stringResource(R.string.about_dev_name),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Descripción corta o título profesional
        SpotiSubtitleText(
            text = stringResource(R.string.about_dev_role),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Bio
        SpotiBodyText(
            text = stringResource(R.string.about_dev_about),
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Información adicional estilo "stats"
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                //Dummy data
                InfoRow(label = "Seguidores", value = "128")
                InfoRow(label = "Siguiendo", value = "89")
                InfoRow(label = "Miembro desde", value = "2020")
            }
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SpotiBodyText(
            text = label,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        )
        SpotiBodyText(
            text = value,
            fontWeight = FontWeight.SemiBold
        )
    }
}
