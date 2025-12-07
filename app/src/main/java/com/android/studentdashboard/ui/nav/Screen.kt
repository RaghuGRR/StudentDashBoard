package com.android.studentdashboard.ui.nav

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object Home : Screen("home")
}