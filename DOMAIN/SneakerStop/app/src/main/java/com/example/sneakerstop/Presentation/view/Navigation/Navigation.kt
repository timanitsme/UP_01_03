package com.example.sneakerstop.Presentation.view.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sneakerstop.Presentation.view.Authorization
import com.example.sneakerstop.Presentation.view.Catalog
import com.example.sneakerstop.Presentation.view.FavoriteScreen
import com.example.sneakerstop.Presentation.view.Home
import com.example.sneakerstop.Presentation.view.LoyaltyCard
import com.example.sneakerstop.Presentation.view.NewPassword
import com.example.sneakerstop.Presentation.view.ProfileScreen
import com.example.sneakerstop.Presentation.view.Registration
import com.example.sneakerstop.Presentation.view.ResetPassword
import com.example.sneakerstop.Presentation.view.SplashScreen
import com.example.sneakerstop.Presentation.view.VerificationScreen

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
        composable("homeScreen"){
            Home(navController)
        }
        composable("favoriteScreen"){
            FavoriteScreen(navController)
        }
        composable("profileScreen"){
            ProfileScreen(navController)
        }
        composable("loyaltyCardScreen"){
            LoyaltyCard(navController)
        }

        composable("catalog/{catalogId}"){backStackEntry ->
            val catalogId = backStackEntry.arguments?.getString("catalogId")
            if (catalogId != null){
                Catalog(navController, catalogId)
            }
        }
        composable("newPasswordScreen/{email}/{otp}"){backStackEntry ->
            val email = backStackEntry.arguments?.getString("email")
            val otp = backStackEntry.arguments?.getString("otp")
            if (email != null && otp != null){
                NewPassword(navController, email, otp)
            }
        }
        composable("verificationScreen/{email}"){ backStackEntry ->
            val email = backStackEntry.arguments?.getString("email")
            if (email != null)
                VerificationScreen(navController, email)
        }


    }
}