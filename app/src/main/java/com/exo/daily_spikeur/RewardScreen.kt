package com.exo.daily_spikeur

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.exo.daily_spikeur.R

@Composable
fun RewardScreen() {
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

        // Image de la récompense
        Image(
            painter = painterResource(id = R.drawable.capture_d_cran_2024_10_06___10_02_58), // Remplacez par votre image
            contentDescription = "Reward Image",
            modifier = Modifier
                .size(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Barre de progression (texte)
        Text(
            text = "100 / 100",
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
    }
}
