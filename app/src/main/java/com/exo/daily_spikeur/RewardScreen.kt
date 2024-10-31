package com.exo.daily_spikeur
import MainViewModel
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RewardScreen(viewModel: MainViewModel) {

    var points = viewModel.getPoints()
    var currentReward by remember { mutableStateOf<Int?>(null) }
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(0xFFEDE1DB))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "CLICK TO GET REWARD",
            fontFamily = FontFamily(Font(R.font.test)),
            fontSize = 30.sp,
            color = Color(0xFF916953),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = if (points >= 100) R.drawable.reward_pack else R.drawable.no_reward),
            contentDescription = "Reward Image",
            modifier = Modifier
                .size(200.dp)
                .clickable(enabled = points >= 100) {
                    if (points >= 100) {
                        showDialog = true
                        currentReward = viewModel.openChest()
                    }
                }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "$points / 100",
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier
                .background(Color(0xFF4B3827))
                .padding(horizontal = 32.dp, vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

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

        val context = LocalContext.current
        if (currentReward == -1) {
            Toast.makeText(
                context,
                "Vous possédez déjà tous les Poopers...\n Attendez la prochaine mise à jour !",
                Toast.LENGTH_SHORT
            ).show()
        }
        else if (currentReward == -2) {
            Toast.makeText(
                context,
                "Une erreur inattendue est survenue lors de la sélection de l'image.",
                Toast.LENGTH_SHORT
            ).show()
        }
        else if (currentReward != null) {
        PopUpWithImage(
            imageId = viewModel.getImageById(currentReward!!),
            showDialog = showDialog,
            closeBtnText = "Keep my Pooper",
            onClose = {
                showDialog = false
            })
        }
    }
}