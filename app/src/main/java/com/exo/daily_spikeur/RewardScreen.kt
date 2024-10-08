package com.exo.daily_spikeur
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@Composable
fun RewardScreen() {
    // Gestion des points et de l'image de récompense
    var points by remember { mutableStateOf(100) } // Remplace par 100 pour simuler l'atteinte du seuil
    var currentReward by remember { mutableStateOf(R.drawable.base_3) } // Image de profil par défaut

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Texte "Your Reward"
        Text(
            text = "YOUR REWARD",
            fontFamily = FontFamily(Font(R.font.test)), // Remplacez par votre police
            fontSize = 30.sp,
            color = Color(0xFF916953),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Image du coffre, clickable si les points atteignent 100
        Image(
            painter = painterResource(id = if (points >= 100) R.drawable.capture_d_cran_2024_10_06___10_02_58 else R.drawable.couronner),
            contentDescription = "Reward Image",
            modifier = Modifier
                .size(200.dp)
                .clickable(enabled = points >= 100) {
                    if (points >= 100) {
                        currentReward = openChest() // Ouvrir le coffre et obtenir une nouvelle image
                        points = 0 // Réinitialiser les points après l'ouverture du coffre
                    }
                }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Affichage de la barre de progression sous forme de texte
        Text(
            text = "$points / 100",
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier
                .background(Color(0xFF4B3827))
                .padding(horizontal = 32.dp, vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Texte des probabilités
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "> 0.5% LEGENDARY", color = Color(0xFFFDB933), fontSize = 18.sp)
            Text(text = "> 5.5% MYTHIC", color = Color(0xFF744FC6), fontSize = 18.sp)
            Text(text = "> 25% RARE", color = Color(0xFF6DBD29), fontSize = 18.sp)
            Text(text = "> 69% COMMON", color = Color(0xFF8B83FF), fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Affichage de la récompense gagnée
        Image(
            painter = painterResource(id = currentReward),
            contentDescription = "Current Reward Image",
            modifier = Modifier.size(100.dp)
        )
    }
}

fun openChest(): Int {
    val legendaryImages = listOf(
        R.drawable.legendary_1, R.drawable.legendary_2, R.drawable.legendary_3,
        R.drawable.legendary_4, R.drawable.legendary_5, R.drawable.legendary_6
    )
    val mythicImages = listOf(
        R.drawable.mythic_1, R.drawable.mythic_2, R.drawable.mythic_3,
        R.drawable.mythic_4, R.drawable.mythic_5, R.drawable.mythic_6,
        R.drawable.mythic_7, R.drawable.mythic_8, R.drawable.mythic_9
    )
    val rareImages = listOf(
        R.drawable.rare_1, R.drawable.rare_2, R.drawable.rare_3,
        R.drawable.rare_4, R.drawable.rare_5, R.drawable.rare_6,
        R.drawable.rare_7, R.drawable.rare_8
    )
    val commonImages = listOf(
        R.drawable.common_1, R.drawable.common_2, R.drawable.common_3,
        R.drawable.common_4, R.drawable.common_5, R.drawable.common_6,
        R.drawable.common_7
    )

    val randomValue = Random.nextDouble()

    return when {
        randomValue <= 0.002 -> {
            legendaryImages.random()
        }
        randomValue <= 0.06 -> {
            mythicImages.random()
        }
        randomValue <= 0.31 -> {
            rareImages.random()
        }
        else -> {
            commonImages.random()
        }
    }
}