package com.example.sneakerstop.view

import android.graphics.Bitmap
import android.util.Log
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sneakerstop.R
import com.example.sneakerstop.domain.utils.Constants
import com.example.sneakerstop.domain.utils.generateBarcode
import com.example.sneakerstop.model.Categories
import com.example.sneakerstop.model.Products
import com.example.sneakerstop.ui.theme.Accent
import com.example.sneakerstop.ui.theme.Background
import com.example.sneakerstop.ui.theme.Black
import com.example.sneakerstop.ui.theme.Block
import com.example.sneakerstop.ui.theme.BodySmallMedium
import com.example.sneakerstop.ui.theme.BodySmallSemiBold
import com.example.sneakerstop.ui.theme.BodyUltraSmall
import com.example.sneakerstop.viewmodel.MainViewModel
import io.github.jan.supabase.gotrue.auth

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun LoyaltyCard(navController: NavController){
    var barcodeBitmap by remember { mutableStateOf<Bitmap?>(null) }


    LaunchedEffect(Unit){
        val currentUser = Constants.supabase.auth.currentUserOrNull()
        if (currentUser != null)
            barcodeBitmap = generateBarcode(currentUser.id, width = 590, height = 200)
    }

    Column(modifier= Modifier.fillMaxSize().background(Background).padding(horizontal = 20.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 50.dp),
            horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {navController.popBackStack()}, modifier = Modifier.clip(shape = RoundedCornerShape(50.dp)).background(
                Block
            )) {
                Icon(painterResource(R.drawable.back_icon), "", tint= com.example.sneakerstop.ui.theme.Text)
            }
            Text(text = "Карта лояльности", style = BodySmallSemiBold, color= Black)
            Spacer(modifier = Modifier.width(30.dp))
        }
        Column(modifier = Modifier.fillMaxSize().padding(vertical = 23.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(240.dp, 650.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Block),
                contentAlignment = Alignment.Center
            ) {
                if (barcodeBitmap != null) {
                    Image(
                        bitmap = barcodeBitmap!!.asImageBitmap(),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(8.dp))
                            .rotate(90f),
                        contentScale = ContentScale.FillBounds
                    )
                }
            }
        }


    }
}