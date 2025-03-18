package com.example.sneakerstop.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakerstop.domain.utils.Constants
import com.example.sneakerstop.model.ProfileCreate
import io.github.jan.supabase.gotrue.SignOutScope
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    fun signIn(userEmail: String, userPassword: String, callback: (Boolean) -> Unit){
        viewModelScope.launch {
            try {
                Constants.supabase.auth.signInWith(Email){
                    email = userEmail
                    password = userPassword
                }
                callback(true)
            }
            catch (e: Exception){
                Log.e("signIn error", e.message?:"")
                callback(false)
            }
        }
    }

    fun insertProfile(profile: ProfileCreate, callback: (Boolean) -> Unit){
        viewModelScope.launch {
            try{
                Constants.supabase.from("profiles").insert(profile)
                callback(true)
            }
            catch(e:Exception){
                Log.e("Profile creating error", e.message?:"")
                callback(false)
            }


        }
    }

    fun resetPassword(email: String, callback: (Boolean) -> Unit){
        viewModelScope.launch {
            try {
                Constants.supabase.auth.resetPasswordForEmail(email)
                callback(true)
            }
            catch(e:Exception){
                Log.e("Reset password error", e.message?:"")
                callback(false)
            }
        }
    }

    fun signOut(callback: (Boolean) -> Unit){
        viewModelScope.launch {
            try{
                Constants.supabase.auth.signOut(SignOutScope.LOCAL)
                callback(true)
            }
            catch(e:Exception){
                callback(false)
            }

        }
    }

    fun signUp(userEmail: String, userPassword: String, callback: (Boolean) -> Unit){
        viewModelScope.launch {
            try {
                Constants.supabase.auth.signUpWith(Email){
                    email = userEmail
                    password = userPassword
                }
                callback(true)
            }
            catch (e: Exception){
                Log.e("getBooks error", e.message?:"")
                callback(false)
            }
        }
    }

    fun isLogged(callback: (Boolean) -> Unit){
        viewModelScope.launch {
            try{
                val userId = Constants.supabase.auth.currentUserOrNull()
                if (userId != null){
                    callback(true)
                }
                else{
                    callback(false)
                }
            }
            catch(e:Exception){
                Log.e("CurrentUser error", e.message?: "")
                callback(false)
            }
        }
    }
}