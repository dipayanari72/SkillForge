package com.example.skillforge.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.skillforge.data.AiChatbotRepository
import com.example.skillforge.models.ChatMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AiChatViewModel: ViewModel() {
    private val repository = AiChatbotRepository()
    private val _isLoading = MutableStateFlow(false)
    private val _message = MutableStateFlow(
        listOf(
            ChatMessage(
                "Hi! I'm your SkillForge AI assistant. Ask me about Kotlin, Android, or your courses!",
                false)
        )
    )
    val message = _message.asStateFlow()
    val isLoading = _isLoading.asStateFlow()

    fun sendMessage(message: String){
        val question = message.trim()
        if (question.isEmpty() || isLoading.value) return
        _message.value += ChatMessage(question,true)
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val answer = repository.sendMessage(question)
                _message.value += ChatMessage(answer,false)
            }catch (e: Exception){
                Log.e("AiChatViewModel", "AI message failed", e)
                _message.value += ChatMessage(
                    "Sorry, something went wrong. Please try again.",
                    false
                )
            } finally {
                _isLoading.value = false
            }
        }
    }
}