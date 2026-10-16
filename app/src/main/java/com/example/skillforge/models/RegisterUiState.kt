package com.example.skillforge.models

data class RegisterUiState(
    val name : String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val errorMessage: String? = null,
    val registerSuccess : Boolean = false
)
