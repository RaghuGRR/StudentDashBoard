package com.android.studentdashboard.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.studentdashboard.ui.components.home.HomeScreen
import com.android.studentdashboard.ui.components.login.LoginScreen
import com.android.studentdashboard.ui.viewmodels.LoginViewModel



@Composable
fun Navigation() {
    val navController = rememberNavController()

    val loginViewModel: LoginViewModel = hiltViewModel()

    val isStudentLogin by loginViewModel.isUserLoggedIn.collectAsState()

    val startDestination = if (isStudentLogin) Screen.Home.route else Screen.Login.route

    NavHost(
        navController,
        startDestination = startDestination
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(
                onLogout = {
                    loginViewModel.logout()
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                }
            )
        }
    }
}