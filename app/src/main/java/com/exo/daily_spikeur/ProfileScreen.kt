package com.exo.daily_spikeur

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
import com.exo.daily_spikeur.R

class UserProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DailyspikeurTheme {
                UserProfileScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen() {
    val profileImageResId = remember { mutableStateOf(R.drawable.base_3) } // Image de profil par défaut

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFEDE1DB)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Image de Profil
        Image(
            painter = painterResource(id = profileImageResId.value),
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
            PooperItem(imageResId = R.drawable.legendary_5) { profileImageResId.value = R.drawable.legendary_5 }
            PooperItem(imageResId = R.drawable.honor_millat) { profileImageResId.value = R.drawable.honor_millat }
            PooperItem(imageResId = R.drawable.pixlr_image_generator_d67c81b7_60aa_4cb1_99bf_18a3bce76ea7) { profileImageResId.value = R.drawable.pixlr_image_generator_d67c81b7_60aa_4cb1_99bf_18a3bce76ea7 }
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
    val displayedImageResId = imageResId ?: R.drawable.base_3 // Remplacez par vos images
    Box(
        modifier = Modifier
            .size(80.dp)
            .clickable { onClick() }
            .padding(4.dp)
    ) {
        Image(
            painter = painterResource(id = displayedImageResId),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserProfileScreenPreview() {
    DailyspikeurTheme {
        UserProfileScreen()
    }
}