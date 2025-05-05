package com.kunduzamatbekova.android.workouttracker.auth

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kunduzamatbekova.android.workouttracker.ViewModel

private val auth = Firebase.auth

@Composable
fun LoginScreen(navController: NavController) {
    val email = viewModel<ViewModel>().email
    val password = viewModel<ViewModel>().password

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ValidateInput()
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("myTAG", "signInWithEmail:success")
                        navController.navigate("profile") {
                            popUpTo("auth") { inclusive = true }
                        }
                    } else {
                        Log.w("myTAG", "signInWithEmail:failure", task.exception)
                    }
                }
        }) {
            Text("Войти")
        }
    }
}