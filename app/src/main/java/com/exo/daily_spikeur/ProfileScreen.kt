package com.exo.daily_spikeur

import UserProfileViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.exo.daily_spikeur.ui.theme.DailyspikeurTheme
import androidx.lifecycle.viewmodel.compose.viewModel

import com.exo.daily_spikeur.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen(viewModel: UserProfileViewModel) {
    val profileImageResId = viewModel.profileImageResId.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFEDE1DB)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Image de Profil
        Image(
            painter = painterResource(id = profileImageResId),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(100.dp)
                .padding(8.dp)
        )

        // Informations Utilisateur
        Text(
            text = "NOM Prénom",
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xFF916953),
            fontFamily = FontFamily(Font(R.font.test))
        )
        Text(
            text = "#ID123456",
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF916953),
            fontFamily = FontFamily(Font(R.font.test))
        )
        Text(
            text = "POOP RANK",
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF916953),
            fontFamily = FontFamily(Font(R.font.test))
        )

        // Statistiques
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            StatCard(label = "TOTAL POOP", value = "12 POOPS")
            StatCard(label = "POOP POINTS", value = "150")
        }

        // Poopers
        Text(
            text = "POOPERS",
            style = MaterialTheme.typography.titleMedium,
            fontFamily = FontFamily(Font(R.font.test)),
            color = Color(0xFF916953),
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // Grille des Poopers
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            PooperItem(imageResId = R.drawable.legendary_5) { viewModel.changeProfileImage(R.drawable.legendary_5) }
            PooperItem(imageResId = R.drawable.honor_millat) { viewModel.changeProfileImage(R.drawable.honor_millat) }
            PooperItem(imageResId = R.drawable.legendary_4) { viewModel.changeProfileImage(R.drawable.legendary_4) }
            PooperItem(isGetMore = true) // Dernier item pour "Get More Poopers"
        }
    }
}

@Composable
fun StatCard(label: String, value: String) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = label, color = Color(0xFF916953), fontFamily = FontFamily(Font(R.font.test)))
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = value, style = MaterialTheme.typography.titleLarge, color = Color.Black)
        }
    }
}

@Composable
fun PooperItem(isGetMore: Boolean = false, imageResId: Int? = null, onClick: () -> Unit = {}) {
    if (isGetMore) {
        // Afficher le bouton "Get More Poopers"
        Box(
            modifier = Modifier
                .size(80.dp)
                .clickable { onClick() } // Appelle onClick si c'est "Get More"
                .padding(4.dp),
            contentAlignment = Alignment.Center // Centre le contenu
        ) {
            // Afficher du texte pour "Get More"
            Text(
                text = "Get More",
                color = Color.Black,
                modifier = Modifier.padding(4.dp)
            )
        }
    } else {
        // Afficher l'image de pooper
        val displayedImageResId = imageResId ?: R.drawable.base_3 // Image par défaut
        Box(
            modifier = Modifier
                .size(80.dp)
                .clickable { onClick() } // Appelle onClick pour changer l'image de profil
                .padding(4.dp)
        ) {
            Image(
                painter = painterResource(id = displayedImageResId),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserProfileScreenPreview() {
    DailyspikeurTheme {
        UserProfileScreen(viewModel())
    }
}
