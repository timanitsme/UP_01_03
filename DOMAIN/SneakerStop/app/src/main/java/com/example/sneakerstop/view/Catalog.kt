package com.example.sneakerstop.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sneakerstop.R
import com.example.sneakerstop.model.Categories
import com.example.sneakerstop.model.Products
import com.example.sneakerstop.ui.theme.Accent
import com.example.sneakerstop.ui.theme.Background
import com.example.sneakerstop.ui.theme.Black
import com.example.sneakerstop.ui.theme.Block
import com.example.sneakerstop.ui.theme.BodySmallMedium
import com.example.sneakerstop.ui.theme.BodySmallSemiBold
import com.example.sneakerstop.ui.theme.BodyUltraSmall
import com.example.sneakerstop.ui.theme.Red
import com.example.sneakerstop.view.Navigation.BottomNav
import com.example.sneakerstop.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Catalog(navController: NavController, categoryId: String){
    var isLoading by remember { mutableStateOf(true) }
    var products by remember { mutableStateOf(listOf<Products>()) }
    var categories by remember { mutableStateOf(listOf<Categories>()) }
    var currentCategory by remember { mutableStateOf<Categories?>(null) }
    val vm = MainViewModel()


    LaunchedEffect(isLoading){
        if (isLoading){
            vm.getProductsByCategory(categoryId) { result ->
                if (result != null){
                    products = result
                }
                else{
                    Log.e("Catalog products", "Продукты не получены")
                }
            }
            vm.getCategories { result ->
                if (result != null){
                    categories = result
                }
                else{
                    Log.e("Catalog categories", "Категории не получены")
                }
            }
            vm.getCategoryById(categoryId){result ->
                if (result != null){
                    currentCategory = result
                }
                else{
                    Log.e("Catalog current category", "Текущая категория не получена")
                }
            }
            isLoading = false
        }
    }

    Column(modifier= Modifier.fillMaxSize().background(Background).padding(horizontal = 20.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 50.dp),
            horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {navController.navigate("homeScreen")}, modifier = Modifier.clip(shape = RoundedCornerShape(50.dp)).background(
                Block
            )) {
                Icon(painterResource(R.drawable.back_icon), "", tint= com.example.sneakerstop.ui.theme.Text)
            }
            if (!isLoading) Text(text = currentCategory?.title?:"", style = BodySmallSemiBold, color= Black)
            Spacer(modifier = Modifier.width(30.dp))
        }
        Column(modifier = Modifier.fillMaxWidth().padding(vertical = 23.dp)) {
            Text(modifier = Modifier.fillMaxWidth(),text="Категории", textAlign = TextAlign.Left, style= BodySmallMedium, color= com.example.sneakerstop.ui.theme.Text)
            LazyRow(modifier = Modifier.fillMaxWidth().padding(top = 15.dp)){
                items(categories){category ->
                    Row(modifier = Modifier.padding(end=16.dp).clip(RoundedCornerShape(8.dp)).background(if (categoryId == category.id) Accent else Block).clickable {if (categoryId != category.id) navController.navigate("catalog/${category.id}") }) {
                        Text(
                            text = category.title,
                            style = BodyUltraSmall,
                            modifier = Modifier.padding(horizontal = 30.dp, vertical = 11.dp),
                            color = com.example.sneakerstop.ui.theme.Text
                        )
                    }
                }
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
}