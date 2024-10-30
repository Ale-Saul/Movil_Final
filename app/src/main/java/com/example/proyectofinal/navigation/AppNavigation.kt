package com.example.proyectofinal.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinal.screen.HomeScreen
import com.example.proyectofinal.screen.LoginScreen
import com.example.proyectofinal.screen.MovieDetailScreen
import com.example.proyectofinal.screen.RegisterScreen
import com.example.proyectofinal.screen.WelcomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.WelcomeScreen.route
    ) {
        composable(Screens.WelcomeScreen.route) {
            WelcomeScreen(
                onClick = {
                    navController.navigate(Screens.RegisterScreen.route)
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
            HomeScreen(onClick = {
                navController.navigate(Screens.MovieDetailScreen.route)
            })
        }
        composable(Screens.RegisterScreen.route) {
            RegisterScreen(
                onClick = {
                    navController.navigate(Screens.LoginScreen.route)
                }
            )
        }
        composable(Screens.MovieDetailScreen.route) {
            MovieDetailScreen()
        }

    }
}