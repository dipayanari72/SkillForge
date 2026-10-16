package com.example.skillforge.viewmodels

import androidx.lifecycle.ViewModel
import com.example.skillforge.models.RegisterUiState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegisterViewModel: ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState = _uiState.asStateFlow()
    fun onNameChange(value : String){
        _uiState.value = _uiState.value.copy(
            name = value
        )
    }
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
    fun onConfirmPasswordChange(value : String){
        _uiState.value = _uiState.value.copy(
            confirmPassword = value
        )
    }
    fun register(){
        val state = _uiState.value
        if (state.name.isBlank()){
            _uiState.value = state.copy(
                errorMessage = "Name is required"
            )
            return
        }
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
        if (state.confirmPassword.isBlank()){
            _uiState.value = state.copy(
                errorMessage = "Confirm Password is required"
            )
            return
        }
        if (state.password != state.confirmPassword) {
            _uiState.value = state.copy(
                errorMessage = "Passwords do not match"
            )
            return
        }
        auth.createUserWithEmailAndPassword(
            state.email.trim(),
            state.password
        ).addOnSuccessListener {
            val uid = auth.currentUser!!.uid
            val user = hashMapOf(
                "name" to state.name,
                "email" to state.email
            )
            //save
            firestore.collection("users")
                .document(uid)
                .set(user)
                .addOnSuccessListener {
                    _uiState.value = _uiState.value.copy(
                        registerSuccess = true
                    )
                }.addOnFailureListener {
                    _uiState.value = _uiState.value.copy(
                        errorMessage = it.message ?: "Could not save name"
                    )
                }
        }.addOnFailureListener {

            _uiState.value = _uiState.value.copy(
                errorMessage = it.message ?: "Registration failed"
            )
        }

    }
}