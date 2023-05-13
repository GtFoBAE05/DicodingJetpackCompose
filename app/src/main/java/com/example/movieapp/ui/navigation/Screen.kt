package com.example.movieapp.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object Detail : Screen("detail/{movieId}") {
        fun createRoute(movieId: Long) = "detail/$movieId"
    }

    object Profile : Screen("profile")
    object Search : Screen("search")
}