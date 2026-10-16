package com.example.skillforge.data


import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend

class AiChatbotRepository {
    private val model = Firebase.ai(
        backend = GenerativeBackend.googleAI()
    ).generativeModel("gemini-3.5-flash")
    private val chat = model.startChat()
    suspend fun sendMessage(message: String): String{
        val response = chat.sendMessage(message)
        return response.text ?: "Sorry I couldn't generate a response"
    }
}