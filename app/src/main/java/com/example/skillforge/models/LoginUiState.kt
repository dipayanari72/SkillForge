package com.example.skillforge.models

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val errorMessage: String? = null,
    val loginSuccess: Boolean = false
)
