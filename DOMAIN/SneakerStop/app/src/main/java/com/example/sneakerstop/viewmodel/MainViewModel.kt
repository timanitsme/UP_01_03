package com.example.sneakerstop.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakerstop.domain.utils.Constants
import com.example.sneakerstop.domain.utils.Constants.supabase
import com.example.sneakerstop.model.ProfileCreate
import io.github.jan.supabase.gotrue.OtpType
import io.github.jan.supabase.gotrue.SignOutScope
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.AuthProvider
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.gotrue.providers.builtin.OTP
import io.github.jan.supabase.gotrue.user.UserUpdateBuilder
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

    fun signInWithOTP(userEmail: String, userOtp: String, callback: (Boolean) -> Unit){
        viewModelScope.launch {
            try{
                supabase.auth.verifyEmailOtp(type = OtpType.Email.EMAIL, email = userEmail, token = userOtp)
                callback(true)
            }
            catch (e:Exception){
                Log.e("Sign in with OTP errror", e.message ?: "")
                callback(false)
            }
        }
    }

    fun updatePassword(otp: String, newPassword: String, callback: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            try {
                Constants.supabase.auth.updateUser{
                    password = newPassword
                    nonce = otp
                }
                callback(true, "")
            } catch (e: Exception) {
                Log.e("Update password error", e.message ?: "")
                if (e.message == "New password should be different from the old password.")
                    callback(false, "Введен старый пароль")
                else{
                    callback(false, "Некорректный токен")
                }
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