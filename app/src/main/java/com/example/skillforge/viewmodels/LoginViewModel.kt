package com.example.skillforge.viewmodels

import androidx.lifecycle.ViewModel
import com.example.skillforge.models.LoginUiState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    private val auth = FirebaseAuth.getInstance()
    val uiState = _uiState.asStateFlow()

    fun onEmailChange(value : String){
        _uiState.value = _uiState.value.copy(
            email = value
        )
    }
    fun onPasswordChange(value : String){
        _uiState.value = _uiState.value.copy(
            password = value
        )
    }
    fun login(){
        val state = _uiState.value
        if (state.email.isBlank()){
            _uiState.value = state.copy(
                errorMessage = "Email is required"
            )
            return
        }
        if (state.password.isBlank()){
            _uiState.value = state.copy(
                errorMessage = "Password is required"
            )
            return
        }
        auth.signInWithEmailAndPassword(
            state.email.trim(),
            state.password
        ).addOnSuccessListener {
            _uiState.value = _uiState.value.copy(
                loginSuccess = true
            )
        }.addOnFailureListener {
            _uiState.value = _uiState.value.copy(
                errorMessage = it.message ?: "Login Failed"
            )
        }
        return
    }
}