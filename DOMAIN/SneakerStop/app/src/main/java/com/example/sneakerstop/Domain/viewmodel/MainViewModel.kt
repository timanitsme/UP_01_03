package com.example.sneakerstop.Domain.viewmodel

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakerstop.Domain.utils.Constants
import com.example.sneakerstop.Domain.utils.Constants.supabase
import com.example.sneakerstop.Domain.model.Actions
import com.example.sneakerstop.Domain.model.Categories
import com.example.sneakerstop.Domain.model.Favorites
import com.example.sneakerstop.Domain.model.FavoritesInsert
import com.example.sneakerstop.Domain.model.Products
import com.example.sneakerstop.Domain.model.ProfileCreate
import com.example.sneakerstop.Domain.model.Profiles
import io.github.jan.supabase.gotrue.OtpType
import io.github.jan.supabase.gotrue.SignOutScope
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.storage.UploadStatus
import io.github.jan.supabase.storage.storage
import io.github.jan.supabase.storage.uploadAsFlow
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

    fun updateProfileData(profile: Profiles, callback: (Boolean) -> Unit){
        viewModelScope.launch {
            try{
                Constants.supabase.from("profiles").upsert(profile)
                callback(true)
            }
            catch(e:Exception){
                Log.e("ProfileUpdate error", e.message?:"")
                callback(false)
            }
        }
    }

    fun loadImageToBucket(uri: Uri, context: Context, filename:String, callback: (String?) -> Unit){
        val intputStream = context.contentResolver.openInputStream(uri)
        val byteArray = intputStream?.readBytes()
        val bucket = Constants.supabase.storage.from("profiles")
        val mimeType = context.contentResolver.getType(uri)
        var imagePath = ""
        if (mimeType == "image/png"){
            imagePath = "$filename.png"
        }
        else if (mimeType == "image/jpg"){
            imagePath = "$filename.jpg"
        }
        else if (mimeType == "image/jpeg"){
            imagePath = "$filename.jpeg"
        }
        else{
            callback(null)
        }
        viewModelScope.launch {
            try{
                bucket.uploadAsFlow(imagePath, byteArray!!).collect{ result ->
                    when(result)
                    {
                        is UploadStatus.Success -> {
                            callback(bucket.publicUrl(imagePath))
                        }
                        is UploadStatus.Progress -> Log.d("ImageUpload", "Изображение загружается")
                    }
                }
            }
            catch(e:Exception){
                Log.e("error", e.message?:"")
                callback(null)
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

    fun getProducts(callback: (List<Products>?) -> Unit){
        viewModelScope.launch {
            try{
                val items = Constants.supabase.from("products").select{}.decodeList<Products>()
                callback(items)
            }
            catch(e:Exception){
                Log.e("getProducts error", e.message?:"")
                callback(null)
            }
        }
    }

    fun getCurrentUserProfile(callback: (Profiles?) -> Unit){
        viewModelScope.launch {
            try{
                val item = Constants.supabase.from("profiles").select{filter { Profiles::user_id eq Constants.supabase.auth.currentUserOrNull()!!.id }}.decodeSingle<Profiles>()
                callback(item)
            }
            catch(e:Exception){
                Log.e("getCurrentUserProfile error", e.message?:"")
                callback(null)
            }
        }
    }


    fun getProductsByCategory(categoryId: String, callback: (List<Products>?) -> Unit){
        viewModelScope.launch {
            try{
                val items = Constants.supabase.from("products").select{ filter { Products::category_id eq categoryId }}.decodeList<Products>()
                callback(items)
            }
            catch(e:Exception){
                Log.e("getProductsByCategory error", e.message?:"")
                callback(null)
            }
        }
    }


    fun getCategoryById(categoryId: String, callback: (Categories?) -> Unit){
        viewModelScope.launch {
            try{
                val item = Constants.supabase.from("categories").select{ filter { Categories::id eq categoryId }}.decodeSingle<Categories>()
                callback(item)
            }
            catch(e:Exception){
                Log.e("getCategoryById error", e.message?:"")
                callback(null)
            }
        }
    }

    fun insertFavorite(productId: String, callback: (Boolean) -> Unit){
        viewModelScope.launch {
            try{
                Constants.supabase.from("favourite").insert(FavoritesInsert(product_id = productId, user_id = supabase.auth.currentUserOrNull()!!.id))
                callback(true)
            }
            catch(e:Exception){
                Log.e("Profile creating error", e.message?:"")
                callback(false)
            }
        }
    }

    fun deleteFromFavorites(productId: String, callback: (Boolean) -> Unit){
        viewModelScope.launch {
            try{
                Constants.supabase.from("favourite").delete{
                    filter { and {
                        Favorites::product_id eq productId
                        Favorites::user_id eq supabase.auth.currentUserOrNull()!!.id
                    }  }
                }
                callback(true)
            }
            catch(e: Exception){
                Log.e("FavoriteDelete error", e.message?:"")
                callback(false)
            }
        }
    }

    fun getIsFavorite(productId: String, callback: (Boolean) -> Unit){
        viewModelScope.launch {
            try{
                val results = Constants.supabase.from("favourite").select{ filter { and{
                    Favorites::product_id eq productId
                    Favorites::user_id eq Constants.supabase.auth.currentUserOrNull()!!.id
                }   }}.decodeSingleOrNull<Favorites>()
                if (results == null){
                    callback(false)
                }
                else{
                    callback(true)
                }
            }
            catch(e:Exception){
                Log.e("getIsFavorite error", e.message?:"")
                callback(false)
            }
        }
    }


    fun getFavorites(callback: (List<Products>?) -> Unit){
        viewModelScope.launch {
            try{
                val items = Constants.supabase.from("favourite").select{ filter { Favorites::user_id eq Constants.supabase.auth.currentUserOrNull()!!.id }}.decodeList<Favorites>()
                val products = mutableListOf<Products>()
                items.forEach{item ->
                    val product = Constants.supabase.from("products").select { filter{ Products::id eq item.product_id} }.decodeSingle<Products>()
                    products.add(product)
                }
                callback(products)
            }
            catch(e:Exception){
                Log.e("getFavorites error", e.message?:"")
                callback(null)
            }
        }
    }



    fun getCategories(callback: (List<Categories>?) -> Unit){
        viewModelScope.launch {
            try{
                val items = Constants.supabase.from("categories").select{}.decodeList<Categories>()
                callback(items)
            }
            catch(e:Exception){
                Log.e("getCategories error", e.message?:"")
                callback(null)
            }
        }
    }

    fun getActions(callback: (List<Actions>?) -> Unit){
        viewModelScope.launch {
            try{
                val items = Constants.supabase.from("actions").select{}.decodeList<Actions>()
                callback(items)
            }
            catch(e:Exception){
                Log.e("getActions error", e.message?:"")
                callback(null)
            }
        }
    }


}