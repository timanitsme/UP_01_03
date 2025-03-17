package com.example.sneakerstop.view.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sneakerstop.view.Authorization

@Composable
fun Navigation(start: String = "authScreen"){
    val navController = rememberNavController()
    NavHost(navController, start){
        composable("authScreen"){
            Authorization() // navController
        }
    }
}