package com.example.sneakerstop.Presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.sneakerstop.R
import com.example.sneakerstop.Presentation.ui.theme.Accent
import kotlinx.coroutines.delay

@Preview
@Composable
fun SplashScreen(navController: NavController){
    LaunchedEffect(key1 = true) {
        delay(1000L)
        navController.navigate("authScreen"){
            popUpTo("splashScreen"){
                inclusive = true
            }
        }

    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Accent),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Image(
            painter = painterResource(R.drawable.splash_logo_icon),
            contentDescription = "Логотип приложения",
        )
    }


}