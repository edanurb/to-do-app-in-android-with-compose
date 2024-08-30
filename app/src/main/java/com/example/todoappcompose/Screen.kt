package com.example.todoappcompose

import androidx.compose.runtime.Composable

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Menu : Screen("search")
    object Profile : Screen("profile")
    object Quick : Screen("quick")
    object AddTask: Screen("addTask")
    object DailyTasks:Screen("dailyTasks")
    object MonthlyTasks: Screen("monthlyTasks")
}

