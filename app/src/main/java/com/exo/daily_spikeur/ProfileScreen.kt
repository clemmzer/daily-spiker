package com.exo.daily_spikeur

import MainViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun UserProfileScreen(viewModel: MainViewModel,navController: NavController) {
    val user by viewModel.user

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEDE1DB))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = viewModel.getImageById(user.photo)),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(100.dp)
                .padding(8.dp)
        )

        Text(
            text = "${user.firstname} ${user.lastname}",
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xFF916953),
            fontFamily = FontFamily(Font(R.font.test))
        )
        Text(
            text = user.fakeId,
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF916953),
            fontFamily = FontFamily(Font(R.font.test))
        )
        Text(
            text = "POOP RANK : 2",
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF916953),
            fontFamily = FontFamily(Font(R.font.test))
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            StatCard(label = "TOTAL POOP", value = "${user.poop_count} POOPS")
            StatCard(label = "POOP POINTS", value = "${user.points}")
        }

        Text(
            text = "POOPERS",
            style = MaterialTheme.typography.titleMedium,
            fontFamily = FontFamily(Font(R.font.test)),
            color = Color(0xFF916953),
            modifier = Modifier.padding(vertical = 8.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(user.poopers.size) { index ->
                val pooperId = user.poopers[index]
                PooperItem(pooperId = pooperId, navController = navController, viewModel = viewModel)
            }

            item {
                PooperItem(isGetMore = true, navController = navController, viewModel = viewModel)
            }
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
fun PooperItem(isGetMore: Boolean = false, pooperId: Int? = null,navController: NavController, viewModel: MainViewModel) {
    var showDialog by remember { mutableStateOf(false) }

    if (isGetMore) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clickable {  navController.navigate("reward") }
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Get More",
                color = Color.Black,
                modifier = Modifier.padding(4.dp)
            )
        }
    } else {
        val imageResId = viewModel.getImageById(pooperId!!)
        Box(
            modifier = Modifier
                .size(80.dp)
                .clickable {
                    showDialog = true
                }
                .padding(4.dp)
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }

        if (showDialog) {
            PopUpWithImage(
                showDialog = showDialog,
                imageId = imageResId,
                closeBtnText = "Choose this one",
                onClose = {
                    showDialog = false
                },
                onClick = {
                    viewModel.changeProfilImage(pooperId!!)
                    showDialog = false
                })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserProfileScreenPreview() {
    DailyspikeurTheme {
        val navController = rememberNavController()
        UserProfileScreen(viewModel(), navController)
    }
}
