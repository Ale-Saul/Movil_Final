package com.example.proyectofinal.navigation

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.proyectofinal.screen.BottomNavigationBar
import com.example.proyectofinal.screen.CinemaScreen
import com.example.proyectofinal.screen.FavoritesScreen
import com.example.proyectofinal.screen.HomeScreen
import com.example.proyectofinal.screen.LoginScreen
import com.example.proyectofinal.screen.MovieDetailScreen
import com.example.proyectofinal.screen.ProfileScreen
import com.example.proyectofinal.screen.RegisterScreen
import com.example.proyectofinal.screen.WelcomeScreen
import com.example.proyectofinal.screen.CinemaDetailScreen
import com.example.proyectofinal.viewModel.CinemaViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController)}
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screens.WelcomeScreen.route,
            Modifier.padding(innerPadding)
        ) {
            composable(Screens.WelcomeScreen.route) {
                WelcomeScreen(
                    onClick = {
                        navController.navigate(Screens.RegisterScreen.route)
                    },
                    onSkip = { navController.navigate(Screens.HomeScreen.route) }
                )
            }
            composable(Screens.RegisterScreen.route) {
                RegisterScreen(
                    onClick = {
                        navController.navigate(Screens.LoginScreen.route)
                    }
                )
            }
            composable(Screens.LoginScreen.route) {
                LoginScreen(
                    onClick = {
                        navController.navigate(Screens.HomeScreen.route)
                    }
                )
            }
            composable(Screens.HomeScreen.route) {
                HomeScreen(onClick = { movieId ->
                    Log.e("Navigation", "Navigating to MovieDetailScreen with movieId: $movieId")
                    navController.navigate("movie_detail_screen/$movieId")
                },
                    onNavigateToCinemas = { // Nuevo callback para navegar a la pantalla de Cines
                        navController.navigate(Screens.Cines.route)
                    }
                    )
            }
            composable(
                route = "movie_detail_screen/{movieId}",
                arguments = listOf(navArgument("movieId") { type = NavType.IntType })
            ) { backStackEntry ->
                val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
                MovieDetailScreen(
                    movieId = movieId,
                    onBackPressed = {
                        navController.popBackStack()
                    }
                )
            }

            composable("home") {
                HomeScreen(
                    onClick = { movieId ->
                        Log.e("Navigation", "Navigating to MovieDetailScreen with movieId: $movieId")
                        navController.navigate("movie_detail_screen/$movieId")
                    },
                    onNavigateToCinemas = { // Nuevo callback para navegar a la pantalla de Cines
                        navController.navigate(Screens.Cines.route)
                    }
                )
            }
            composable("favorites") { FavoritesScreen() }
            composable("profile") { ProfileScreen() }

            composable(Screens.Cines.route) {
                val viewModel = CinemaViewModel()
                CinemaScreen(
                    cinemaList = viewModel.cinemaList,
                    onCinemaClick = { cinemaId ->
                        Log.e("CinemaScreen", "Clicked on cinemaId: $cinemaId")
                        navController.navigate("cinema_detail/$cinemaId") // Navega a la pantalla de detalles
                    },
                    onBackPressed = {
                        navController.popBackStack() // Regresa a la pantalla anterior
                    }
                )
            }

            composable("cinema_detail/{cinemaId}") { backStackEntry ->
                val cinemaId = backStackEntry.arguments?.getString("cinemaId")?.toIntOrNull()

                if (cinemaId != null) { // Verifica que cinemaId no sea nulo
                    val viewModel = CinemaViewModel() // Usa el ViewModel para obtener los datos
                    val cinema = viewModel.getCinemaById(cinemaId)

                    cinema?.let {
                        CinemaDetailScreen(
                            cinema = it,
                            onBackPressed = {
                                navController.popBackStack() // Regresa a la pantalla anterior
                            }
                        )
                    }
                } else {
                    Log.e("CinemaDetail", "Invalid cinemaId")
                    navController.popBackStack() // Regresa a la pantalla anterior si no hay un ID válido
                }
            }

        }

    }

}