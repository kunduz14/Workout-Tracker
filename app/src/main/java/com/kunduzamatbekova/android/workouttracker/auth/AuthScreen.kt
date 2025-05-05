package com.kunduzamatbekova.android.workouttracker.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AuthScreen(navController: NavController) {
    var isLoginScreen by remember { mutableStateOf(true) }
    val primaryColor = Color(0xFF00658A)
    val secondaryColor = Color.White

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 160.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AuthButtonRow(
            isLoginScreen,
            primaryColor,
            secondaryColor,
            onLoginClick = { isLoginScreen = true },
            onRegisterClick = { isLoginScreen = false }
        )
        Spacer(modifier = Modifier.height(24.dp))

        if (isLoginScreen) {
            LoginScreen(navController)
        } else {
            RegisterScreen(navController)
        }
    }
}

@Composable
fun AuthButtonRow(
    isLoginScreen: Boolean,
    primaryColor: Color,
    secondaryColor: Color,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AuthButton(
            "Войти",
            isLoginScreen,
            primaryColor,
            secondaryColor,
            RoundedCornerShape(5.dp, 0.dp, 0.dp, 5.dp),
            onLoginClick,
            Modifier.weight(1f)
        )
        AuthButton(
            "Зарегистрироваться",
            !isLoginScreen,
            primaryColor,
            secondaryColor,
            RoundedCornerShape(0.dp, 5.dp, 5.dp, 0.dp),
            onRegisterClick,
            Modifier.weight(1f)
        )
    }
}

@Composable
fun AuthButton(
    text: String,
    isSelected: Boolean,
    primaryColor: Color,
    secondaryColor: Color,
    shape: RoundedCornerShape,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val containerColor = if (isSelected) primaryColor else secondaryColor
    val contentColor = if (isSelected) secondaryColor else primaryColor

    Button(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        colors = ButtonDefaults.buttonColors(containerColor, contentColor),
        border = BorderStroke(1.dp, primaryColor)
    ) {
        Text(text)
    }
}