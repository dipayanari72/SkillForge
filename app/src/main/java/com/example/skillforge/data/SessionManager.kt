package com.example.skillforge.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.datastore by preferencesDataStore("Session")
class SessionManager(private val context: Context) {
    private val login_key = booleanPreferencesKey("is_logged_in")
    val isLoggedIn : Flow<Boolean> = context.datastore.data.map { data ->
        data[login_key] ?: false
    }
    suspend fun login(){

        
        context.datastore.edit { data ->
            data[login_key] = true
        }
    }
    suspend fun logout(){
        context.datastore.edit { data ->
            data[login_key] = false
        }
    }
}