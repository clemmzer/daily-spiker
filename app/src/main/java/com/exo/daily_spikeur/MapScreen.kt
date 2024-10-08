package com.exo.daily_spikeur

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen() {
    Box(modifier = Modifier.fillMaxSize()) {

        // Carte Google
        val cameraPositionState = rememberCameraPositionState()

       /* GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            // Ajoutez vos marqueurs ici, exemple :
            Marker(
              //  position = 23* Position GPS de votre marqueur
                title = "Poop Location"
            )
        }*/

        // Texte centré sur la carte
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Premier texte
            Text(
                text = "FIND YOUR POOP",
                fontFamily = FontFamily(Font(R.font.test)), // Utiliser la police Rubik Bubbles
                color = Color(0xFF8B4513), // Couleur marron
                style = MaterialTheme.typography.headlineMedium
            )

            // Deuxième texte
            Text(
                text = "LESS BLA-BLA MORE POOPING",
                fontFamily = FontFamily(Font(R.font.test)),
                color = Color(0xFF8B4513),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        // Image "Poopy" en bas à gauche
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Image(
                painter = painterResource(id = R.drawable.like), // Remplacer par votre image de Poopy
                contentDescription = "Poopy Icon",
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Fit
            )

            // Texte du nombre de poops en bas à droite
            Text(
                text = "12 Poopy's",
                fontFamily = FontFamily(Font(R.font.test)),
                color = Color(0xFF8B4513),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
