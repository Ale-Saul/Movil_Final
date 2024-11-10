package com.example.proyectofinal

data class FormState(
    val username: String = "",
    val birthDate: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
)

fun validateForm(state: FormState): Boolean {
    return state.username.isNotBlank() &&
            state.birthDate.isNotBlank() &&
            state.email.isNotBlank() &&
            state.email.contains("@") &&
            state.password.isNotBlank() &&
            state.password.length >= 8 &&
            state.confirmPassword.isNotBlank() &&
            state.confirmPassword == state.password
}