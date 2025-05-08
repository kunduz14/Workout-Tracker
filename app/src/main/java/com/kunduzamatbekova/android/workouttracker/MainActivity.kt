package com.kunduzamatbekova.android.workouttracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kunduzamatbekova.android.workouttracker.auth.AuthScreen
import com.kunduzamatbekova.android.workouttracker.screen.MainScreen
import com.kunduzamatbekova.android.workouttracker.ui.theme.WorkoutTrackerTheme

class MainActivity : ComponentActivity() {
    private val auth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WorkoutTrackerTheme {
                if (auth.currentUser == null) {
                    Navigation()
                } else {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "auth") {
        composable("auth") {
            AuthScreen(navController)
        }
        composable("main") {
            MainScreen()
        }
    }
}