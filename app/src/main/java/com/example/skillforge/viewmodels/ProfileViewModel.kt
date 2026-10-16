package com.example.skillforge.viewmodels

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class ProfileViewModel: ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    fun logout(){
        auth.signOut()
    }
}