package com.compose.authentication.utils

sealed class Screen(val route: String) {
    object Login : Screen("sign_in")
    object Register : Screen("sign_up")
    object Dashboard : Screen("dashboard")
}