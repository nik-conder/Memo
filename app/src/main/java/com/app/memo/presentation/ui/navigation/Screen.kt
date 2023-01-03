package com.app.memo.presentation.ui.navigation

sealed class Screen(val route: String) {
    object StartScreen : Screen(route = "startScreen")
    object HomeScreen : Screen(route = "homeScreen")
}