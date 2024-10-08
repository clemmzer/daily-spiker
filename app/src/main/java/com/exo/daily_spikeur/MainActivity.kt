package com.exo.daily_spikeur

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.exo.daily_spikeur.ui.theme.DailyspikeurTheme
import com.exo.daily_spikeur.R
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DailyspikeurTheme {
                ScaffoldWithBottomNav()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldWithBottomNav() {
    val icons = listOf(
        R.drawable.carte, // Remplacez par vos icônes dans drawable
        R.drawable.cadeaux,
        R.drawable.podium, // Assurez-vous que c'est bien l'icône de la couronne
        R.drawable.photo
    )
    val selectedIndex = remember { mutableStateOf(0) }
    val navController = rememberNavController() // Déplacer ici pour l'utiliser partout

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center // Centrer le contenu
                    ) {
                        Text(
                            text = "Daily Spikeur",
                            fontFamily = FontFamily(Font(R.font.test)), // Utilisez la police Rubik Bubbles
                            color = Color.White // Couleur du texte
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {  navController.navigate("profile") }) {
                        Image(
                            painter = painterResource(id = R.drawable.honor_gambier), // Remplacez par votre image
                            contentDescription = "Profile Image",
                            modifier = Modifier.size(50.dp) // Taille de l'image
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF916953)) // Couleur de la barre
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF916953) // Couleur de fond de la barre de navigation
            ) {
                icons.forEachIndexed { index, icon ->
                    NavigationBarItem(
                        icon = {
                            Image(
                                painter = painterResource(id = icon),
                                contentDescription = null,
                                modifier = Modifier.size(40.dp) // Taille de l'icône
                            )
                        },

                        selected = selectedIndex.value == index, onClick = {

                            selectedIndex.value = index
                            when (index) {
                                0 -> navController.navigate("map") // Écran d'accueil
                                1 -> navController.navigate("reward") // Écran des récompenses
                                2 -> navController.navigate("ranking") // Écran de classement
                                3 -> navController.navigate("profile") // Écran de profil
                                // Ajoutez d'autres redirections ici si nécessaire
                                else -> navController.navigate("map") // Redirection par défaut
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.White, // Couleur de l'icône sélectionnée
                            unselectedIconColor = Color.White, // Couleur de l'icône non sélectionnée
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "map",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("map") { MapScreen() }
            composable("reward") { RewardScreen() } // Écran des récompenses
            composable("profile") { UserProfileScreen()  }
            composable("ranking") { RankingScreen()  }
        }
    }
}




@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DailyspikeurTheme {
        Greeting("Android")
    }
}
