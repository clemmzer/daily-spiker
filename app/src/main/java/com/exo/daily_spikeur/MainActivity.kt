package com.exo.daily_spikeur


import CreatePoopAccountScreen
import MainViewModel
import MapScreen
import android.content.pm.ActivityInfo

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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.exo.daily_spikeur.data.datasources.UsersLocalDataSource
import com.exo.daily_spikeur.data.datasources.UsersRemoteDataSource
import com.exo.daily_spikeur.data.datasources.impl.UsersLocalDataSourceImpl
import com.exo.daily_spikeur.data.datasources.impl.UsersRestDataSourceImpl
import com.exo.daily_spikeur.data.repositories.UserRepository
import com.exo.daily_spikeur.data.repositories.impl.UserRepositoryImpl
import com.exo.daily_spikeur.data.retrofit.ApiService
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.exo.daily_spikeur.data.models.pooperMap

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        startKoin {
            androidContext(this@MainActivity)
            modules(repositoryModule,
                dataSourcesModule,
                apiService)
        }
        setContent {
            val viewModel = viewModel<MainViewModel>(
                factory =
                MainViewModel.ViewModelFactory(
                    get()
                )
            )
            viewModel.getUser()

            DailyspikeurTheme {
                ScaffoldWithBottomNav(viewModel)
            }

        }
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    }
}

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(get(), get()) }
}

val dataSourcesModule = module {
    single<UsersLocalDataSource> {
        UsersLocalDataSourceImpl()
    }

    single<UsersRemoteDataSource> {
        UsersRestDataSourceImpl(get())
    }
}

val apiService = module {
    single<ApiService> {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://daily-spiker-api.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldWithBottomNav(viewModel: MainViewModel) {
    val icons = listOf(
        R.drawable.carte,
        R.drawable.cadeaux,
        R.drawable.podium,
        R.drawable.photo
    )
    val selectedIndex = remember { mutableStateOf(0) }
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Daily Spikeur",
                            fontFamily = FontFamily(Font(R.font.test)),
                            color = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {  navController.navigate("profile") }) {
                        Image(
                            painter = painterResource(id = pooperMap[viewModel.user.value.photo] ?: R.drawable.base_1), // Remplacez par votre image
                            contentDescription = "Profile Image",
                            modifier = Modifier.size(50.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF916953))
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF916953)
            ) {
                icons.forEachIndexed { index, icon ->
                    NavigationBarItem(
                        icon = {
                            Image(
                                painter = painterResource(id = icon),
                                contentDescription = null,
                                modifier = Modifier.size(40.dp)
                            )
                        },

                        selected = selectedIndex.value == index, onClick = {

                            selectedIndex.value = index
                            when (index) {
                                0 -> navController.navigate("map") // Écran d'accueil
                                1 -> navController.navigate("reward") // Écran des récompenses
                                2 -> navController.navigate("ranking") // Écran de classement
                                3 -> navController.navigate("profile") // Écran de profil
                                else -> navController.navigate("map") // Redirection par défaut
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.White,
                            unselectedIconColor = Color.White,
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
            composable("reward") { RewardScreen(viewModel) }
            composable("profile") { UserProfileScreen(viewModel, navController)  }
            composable("ranking") { RankingScreen()  }
            composable("connexion") { CreatePoopAccountScreen()  }
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
