package com.example.skillforge.viewmodels

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserViewmodel: ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val fireStore = FirebaseFirestore.getInstance()
    private val _username = MutableStateFlow("User")
    val userName = _username.asStateFlow()

    init {
        getUserName()
    }
    private fun getUserName(){
        val uid = auth.currentUser?.uid?: return
        fireStore.collection("users")
            .document(uid)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()){
                    _username.value = document.getString("name")?: "User"
                }
            }
    }
}