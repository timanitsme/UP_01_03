package com.example.sneakerstop.view.Navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sneakerstop.R
import com.example.sneakerstop.ui.theme.Accent
import com.example.sneakerstop.ui.theme.Block
import com.example.sneakerstop.ui.theme.SubTextDark

@Preview
@Composable
fun BottomNav(currentScreen: String = "homeScreen", navController: NavController) {
    val selectedItem = remember { mutableStateOf(currentScreen) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter){
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
            Row(modifier = Modifier.height(120.dp).fillMaxWidth().background(Block),
                horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically){

            }
            Row(modifier = Modifier.height(120.dp).padding(bottom = 20.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically){
                Icon(painter = painterResource(R.drawable.home_icon), contentDescription = "", tint = if (currentScreen == "homeScreen") Accent else SubTextDark, modifier = Modifier.clickable{if (currentScreen != "homeScreen") navController.navigate("homeScreen") })
                Icon(painter = painterResource(R.drawable.favorite_icon), contentDescription = "", tint = if (currentScreen == "favoriteScreen") Accent else SubTextDark, modifier = Modifier.clickable{if (currentScreen != "favoriteScreen") navController.navigate("favoriteScreen") })
                Box(modifier = Modifier.padding(bottom = 10.dp).clip(CircleShape).background(Accent).size(56.dp), contentAlignment = Alignment.Center){
                    Icon(painter = painterResource(R.drawable.bag_icon), contentDescription = "", tint = Block)
                }
                Icon(painter = painterResource(R.drawable.notification_icon), contentDescription = "", tint = if (currentScreen == "notificationScreen") Accent else SubTextDark)
                Icon(painter = painterResource(R.drawable.profile_icon), contentDescription = "", tint = if (currentScreen == "profileScreen") Accent else SubTextDark, modifier = Modifier.clickable{if (currentScreen != "profileScreen") navController.navigate("profileScreen")})

            }





        }
    }


}