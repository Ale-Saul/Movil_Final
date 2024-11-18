package com.example.proyectofinal.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.data.AppRoomDatabase
import com.example.proyectofinal.screen.HomeScreen
import com.example.proyectofinal.screen.LoginScreen
import com.example.proyectofinal.screen.MovieDetailScreen
import com.example.proyectofinal.screen.RegisterScreen
import com.example.proyectofinal.screen.WelcomeScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val database = remember { AppRoomDatabase.getDatabase(context) }

    var startDestination by remember { mutableStateOf(Screens.WelcomeScreen.route) }
    LaunchedEffect(Unit) {
        val isLoggedIn = withContext(Dispatchers.IO) {
            database.stateDao().isLoggedIn() ?: false
        }
        startDestination = if (isLoggedIn) Screens.HomeScreen.route else Screens.WelcomeScreen.route

    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screens.WelcomeScreen.route) {
            WelcomeScreen(
                onClick = {
                    navController.navigate(Screens.RegisterScreen.route)
                },
                onLoginClick = {
                    navController.navigate(Screens.LoginScreen.route)
                }
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
            })
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

    }
}