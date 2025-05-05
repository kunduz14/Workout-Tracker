package com.kunduzamatbekova.android.workouttracker.auth

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kunduzamatbekova.android.workouttracker.ViewModel

@Composable
fun ValidatingInputTextField(
    email: String,
    password: String,
    updateEmail: (String) -> Unit,
    updatePassword: (String) -> Unit,
    emailHasErrors: Boolean,
    passwordHasErrors: Boolean,
) {
    OutlinedTextField(
        value = email,
        onValueChange = updateEmail,
        placeholder = { Text("Email", color = Color.Gray) },
        isError = emailHasErrors,
        supportingText = {
            if (emailHasErrors) {
                Text("Неправильный формат электронной почты.")
            }
        }
    )
    OutlinedTextField(
        value = password,
        onValueChange = updatePassword,
        placeholder = { Text("Пароль", color = Color.Gray) },
        isError = passwordHasErrors,
        supportingText = {
            if (passwordHasErrors) {
                Text("Неправильный формат пароля.")
            }
        },
        visualTransformation = PasswordVisualTransformation()
    )
}

@Composable
fun ValidateInput() {
    val viewModel: ViewModel = viewModel<ViewModel>()
    ValidatingInputTextField(
        email = viewModel.email,
        password = viewModel.password,
        updateEmail = { input -> viewModel.updateEmail(input) },
        updatePassword = { input -> viewModel.updatePassword(input) },
        emailHasErrors = viewModel.emailHasErrors,
        passwordHasErrors = viewModel.passwordHasErrors,
    )
}