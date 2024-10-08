package com.exo.daily_spikeur

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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

class RankingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DailyspikeurTheme {
                RankingScreen()
            }
        }
    }
}

@Composable
fun RankingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEDE1DB))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "ARE YOU ON THE POOPOSITION ?",
            fontFamily = FontFamily(Font(R.font.test)),
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xFF916953),
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Text(
            text = "RANKING POOPS",
            fontFamily = FontFamily(Font(R.font.test)),
            style = MaterialTheme.typography.titleMedium,
            color = Color(0xFF916953),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Rankings List
        LazyColumn {
            item { RankingItem(name = "CLEMZER", points = "30000 POOPPOINTS (PP)", imageResId = R.drawable.honor_mabile) }
            item { RankingItem(name = "Erwan", points = "29999 POOPPOINTS (PP)", imageResId = R.drawable.legendary_2) }
            item { RankingItem(name = "LIES", points = "2888 POOPPOINTS (PP)", imageResId = R.drawable.pixlr_image_generator_d67c81b7_60aa_4cb1_99bf_18a3bce76ea7) }
            item { RankingItem(name = "YOLO WARRIOR", points = "20000 POOPPOINTS (PP)", imageResId = R.drawable.legendary_6) }
            item { RankingItem(name = "SYNTAX", points = "18000 POOPPOINTS (PP)", imageResId = R.drawable.honor_millat) }
            item { RankingItem(name = "OTHEMGETTO", points = "10000 POOPPOINTS (PP)", imageResId = R.drawable.legendary_5) }
            item { RankingItem(name = "GAMBIER", points = "1000 POOPPOINTS (PP)", imageResId = R.drawable.honor_gambier) }
            item { RankingItem(name = "ORFEO", points = "10000 POOPPOINTS (PP)", imageResId = R.drawable.common_2) }
            item { RankingItem(name = "ALEX", points = "10000 POOPPOINTS (PP)", imageResId = R.drawable.legendary_3) }
            item { RankingItem(name = "THEO PERUS", points = "10000 POOPPOINTS (PP)", imageResId = R.drawable.rare_7) }

        }
    }
}

@Composable
fun RankingItem(name: String, points: String, imageResId: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.White)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = null,
            modifier = Modifier.size(40.dp) // Taille de l'image
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = name, fontFamily = FontFamily(Font(R.font.test)), style = MaterialTheme.typography.bodyLarge,color = Color(0xFF916953),)
            Text(text = points, fontFamily = FontFamily(Font(R.font.test)), style = MaterialTheme.typography.bodyMedium,color = Color(0xFF916953),)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RankingScreenPreview() {
    DailyspikeurTheme {
        RankingScreen()
    }
}
