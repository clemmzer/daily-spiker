package com.exo.daily_spikeur

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun PopUpWithImage(imageId: Int, showDialog: Boolean, closeBtnText: String, onDismiss: () -> Unit) {

    if (showDialog) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = Color(0xFFEDE1DB)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Image
                    Image(
                        painter = painterResource(id = imageId), // Remplace par ton image
                        contentDescription = "Poop Image",
                        modifier = Modifier
                            .size(350.dp),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Bouton "Keep My Pooper"
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4B3827),
                            contentColor = Color.White
                        ),
                        onClick = {
                            // Fermer le dialogue lorsqu'on clique sur le bouton
                            onDismiss()
                        }
                    ) {
                        Text(text = closeBtnText)
                    }
                }
            }
        }
    }
}
