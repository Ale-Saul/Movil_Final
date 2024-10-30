package com.example.proyectofinal.navigation

sealed class Screens (val route: String) {
    object WelcomeScreen: Screens("welcome_screen")
    object LoginScreen: Screens("login_screen")
    object RegisterScreen: Screens("register_screen")
    object HomeScreen: Screens("home_screen")
    object MovieDetailScreen: Screens("detail_screen")

}