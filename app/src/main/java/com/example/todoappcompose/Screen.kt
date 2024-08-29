package com.example.todoappcompose

import androidx.compose.runtime.Composable

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Search : Screen("search")
    object Profile : Screen("profile")
    // Add more screens as needed
}

