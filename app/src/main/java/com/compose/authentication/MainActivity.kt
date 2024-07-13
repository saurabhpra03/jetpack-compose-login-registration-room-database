package com.compose.authentication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.compose.authentication.data.SharedPref
import com.compose.authentication.ui.theme.ComposeAuthenticationTheme
import com.compose.authentication.ui.view.DashboardScreen
import com.compose.authentication.ui.view.LoginScreen
import com.compose.authentication.ui.view.RegistrationScreen
import com.compose.authentication.utils.Screen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var sharedPref: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            val navController = rememberNavController()

            ComposeAuthenticationTheme(
                darkTheme = false,
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                )  {
                    NavHost(navController = navController,
                        startDestination = sharedPref.getAuthDetails()?.let {
                            Screen.Dashboard.route
                        } ?: run {
                            Screen.Login.route
                        }) {

                        composable(Screen.Login.route) {
                            LoginScreen(navController = navController, sharedPref = sharedPref)
                        }

                        composable(Screen.Register.route) {
                            RegistrationScreen(navController = navController, sharedPref = sharedPref)
                        }

                        composable(Screen.Dashboard.route) {
                            DashboardScreen(navController = navController, sharedPref = sharedPref)
                        }
                    }
                }


            }
        }
    }
}