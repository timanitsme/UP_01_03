package com.example.sneakerstop.view.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sneakerstop.view.Authorization
import com.example.sneakerstop.view.NewPassword
import com.example.sneakerstop.view.Registration
import com.example.sneakerstop.view.ResetPassword
import com.example.sneakerstop.view.SplashScreen
import com.example.sneakerstop.view.VerificationScreen

@Composable
fun Navigation(start: String = "authScreen"){
    val navController = rememberNavController()
    NavHost(navController, start){
        composable("splashScreen"){
            SplashScreen(navController)
        }
        composable("authScreen"){
            Authorization(navController)
        }
        composable("regScreen"){
            Registration(navController)
        }
        composable("resetPasswordScreen"){
            ResetPassword(navController)
        }
        composable("newPasswordScreen"){
            NewPassword()
        }
        composable("verificationScreen"){
            VerificationScreen(navController)
        }
    }
}