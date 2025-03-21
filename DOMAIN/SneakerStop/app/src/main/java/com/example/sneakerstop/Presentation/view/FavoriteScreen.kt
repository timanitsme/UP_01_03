package com.example.sneakerstop.Presentation.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sneakerstop.R
import com.example.sneakerstop.Domain.model.Products
import com.example.sneakerstop.Presentation.ui.theme.Background
import com.example.sneakerstop.Presentation.ui.theme.Black
import com.example.sneakerstop.Presentation.ui.theme.Block
import com.example.sneakerstop.Presentation.ui.theme.BodySmallSemiBold
import com.example.sneakerstop.Presentation.ui.theme.Red
import com.example.sneakerstop.Presentation.view.Navigation.BottomNav
import com.example.sneakerstop.Domain.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun FavoriteScreen(navController: NavController){
    var isLoading by remember { mutableStateOf(true) }
    var products by remember { mutableStateOf(listOf<Products>()) }
    val vm = MainViewModel()

    LaunchedEffect(isLoading){
        if (isLoading){
            vm.getFavorites { result ->
                if (result != null){
                    products = result
                }
                else{
                    Log.e("Favorite products", "Продукты не получены")
                }
            }
            isLoading = false
        }
    }

    Column(modifier= Modifier.fillMaxSize().background(Background).padding(horizontal = 20.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 50.dp, bottom = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {navController.popBackStack()}, modifier = Modifier.clip(shape = RoundedCornerShape(50.dp)).background(
                Block
            )) {
                Icon(painterResource(R.drawable.back_icon), "", tint= com.example.sneakerstop.Presentation.ui.theme.Text)
            }
            Text(text = "Избранное", style = BodySmallSemiBold, color= Black)
            IconButton(
                onClick = {},
                modifier = Modifier.clip(shape = RoundedCornerShape(50.dp)).background(
                    Block
                )
            ) {
                Icon(
                    painterResource(R.drawable.favorite_filled_icon),
                    "",
                    tint = Red
                )
            }
        }
        if (!isLoading){
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(products.chunked(2)) { pair ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 15.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        pair.forEachIndexed { index, product ->
                            ProductCard(product = product, onChange = {isLoading = true})
                        }

                        if (pair.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }

    }
    BottomNav(navController = navController, currentScreen = "favoriteScreen")
}