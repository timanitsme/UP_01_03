package com.example.sneakerstop.view

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.sneakerstop.R
import com.example.sneakerstop.model.Categories
import com.example.sneakerstop.model.Products
import com.example.sneakerstop.model.Profiles
import com.example.sneakerstop.ui.theme.Accent
import com.example.sneakerstop.ui.theme.Background
import com.example.sneakerstop.ui.theme.Block
import com.example.sneakerstop.ui.theme.BodySmallInput
import com.example.sneakerstop.ui.theme.BodySmallMedium
import com.example.sneakerstop.ui.theme.BodyUltraSmall
import com.example.sneakerstop.ui.theme.Hint
import com.example.sneakerstop.ui.theme.SubTextDark
import com.example.sneakerstop.viewmodel.MainViewModel

@Composable
fun SideMenu(navController: NavController,onClose: () -> Unit){
    var currentProfile by remember { mutableStateOf<Profiles?>(null) }
    val vm = MainViewModel()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        vm.getCurrentUserProfile {result ->
            if(result != null){
                currentProfile = result
            }
            else{
                Log.e("Side menu profile", "Профиль не получен")
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Accent)
            .padding(horizontal = 20.dp)
            .clickable { onClose() }
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        Box(modifier = Modifier.size(96.dp).clip(CircleShape).background(Background)){
            Image(painter = rememberAsyncImagePainter(currentProfile?.photo), "", modifier = Modifier
                .fillMaxWidth(), alignment = Alignment.Center,
                contentScale = ContentScale.Crop)
        }
        Text(text="${currentProfile?.firstname?:""} ${currentProfile?.lastname?:""}", style = MaterialTheme.typography.titleSmall, color = Block, modifier = Modifier.padding(bottom = 55.dp, top=15.dp))
        Row(modifier = Modifier.fillMaxWidth().padding(end = 40.dp, bottom = 30.dp).clickable{navController.navigate("profileScreen")}, horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
            Row(verticalAlignment = Alignment.CenterVertically){
                Icon(painter = painterResource(R.drawable.profile_icon), contentDescription = "", tint = Block, modifier = Modifier.padding(end=20.dp))
                Text(text="Профиль", style = MaterialTheme.typography.bodySmall, color=Block)
            }
            Icon(painter = painterResource(R.drawable.next_icon), contentDescription = "", tint = Block)
        }
        Row(modifier = Modifier.fillMaxWidth().padding(end = 40.dp, bottom = 30.dp).clickable{}, horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
            Row(verticalAlignment = Alignment.CenterVertically){
                Icon(painter = painterResource(R.drawable.bag_icon), contentDescription = "", tint = Block, modifier = Modifier.padding(end=20.dp))
                Text(text="Корзина", style = MaterialTheme.typography.bodySmall, color=Block)
            }
            Icon(painter = painterResource(R.drawable.next_icon), contentDescription = "", tint = Block)
        }
        Row(modifier = Modifier.fillMaxWidth().padding(end = 40.dp, bottom = 30.dp).clickable{navController.navigate("favoriteScreen")}, horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
            Row(verticalAlignment = Alignment.CenterVertically){
                Icon(painter = painterResource(R.drawable.favorite_icon), contentDescription = "", tint = Block, modifier = Modifier.padding(end=20.dp))
                Text(text="Избранное", style = MaterialTheme.typography.bodySmall, color=Block)
            }
            Icon(painter = painterResource(R.drawable.next_icon), contentDescription = "", tint = Block)
        }
        Row(modifier = Modifier.fillMaxWidth().padding(end = 40.dp, bottom = 30.dp).clickable{}, horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
            Row(verticalAlignment = Alignment.CenterVertically){
                Icon(painter = painterResource(R.drawable.orders_icon), contentDescription = "", tint = Block, modifier = Modifier.padding(end=20.dp))
                Text(text="Заказы", style = MaterialTheme.typography.bodySmall, color=Block)
            }
            Icon(painter = painterResource(R.drawable.next_icon), contentDescription = "", tint = Block)
        }
        Row(modifier = Modifier.fillMaxWidth().padding(end = 40.dp, bottom = 30.dp).clickable{}, horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
            Row(verticalAlignment = Alignment.CenterVertically){
                Icon(painter = painterResource(R.drawable.notification_icon), contentDescription = "", tint = Block, modifier = Modifier.padding(end=20.dp))
                Text(text="Уведомления", style = MaterialTheme.typography.bodySmall, color=Block)
            }
            Icon(painter = painterResource(R.drawable.next_icon), contentDescription = "", tint = Block)
        }
        Row(modifier = Modifier.fillMaxWidth().padding(end = 40.dp, bottom = 30.dp).clickable{}, horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
            Row(verticalAlignment = Alignment.CenterVertically){
                Icon(painter = painterResource(R.drawable.settings_icon), contentDescription = "", tint = Block, modifier = Modifier.padding(end=20.dp))
                Text(text="Настройки", style = MaterialTheme.typography.bodySmall, color=Block)
            }
            Icon(painter = painterResource(R.drawable.next_icon), contentDescription = "", tint = Block)
        }
        HorizontalDivider(thickness = 1.dp, color = Block, modifier = Modifier.fillMaxWidth())
        Row(modifier = Modifier.fillMaxWidth().padding(end = 40.dp, top = 30.dp).clickable{
            vm.signOut { result ->
                if (result){
                    Toast.makeText(context, "Вы вышли из аккаунта", Toast.LENGTH_SHORT).show()
                    navController.navigate("authScreen"){
                        popUpTo("homeScreen"){
                            inclusive = true
                        }
                    }
                }
                else{
                    Toast.makeText(context, "Не удалось выйти", Toast.LENGTH_SHORT).show()
                }
            }
        }, horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically){
            Row(verticalAlignment = Alignment.CenterVertically){
                Icon(painter = painterResource(R.drawable.logout_icon), contentDescription = "", tint = Block, modifier = Modifier.padding(end=20.dp))
                Text(text="Выйти", style = MaterialTheme.typography.bodySmall, color=Block)
            }
        }
    }
}