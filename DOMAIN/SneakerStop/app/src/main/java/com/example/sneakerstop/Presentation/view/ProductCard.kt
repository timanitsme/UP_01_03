package com.example.sneakerstop.Presentation.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.sneakerstop.R
import com.example.sneakerstop.Domain.model.Products
import com.example.sneakerstop.Presentation.ui.theme.Accent
import com.example.sneakerstop.Presentation.ui.theme.Background
import com.example.sneakerstop.Presentation.ui.theme.Block
import com.example.sneakerstop.Presentation.ui.theme.BodySmallInput
import com.example.sneakerstop.Presentation.ui.theme.BodySmallMedium
import com.example.sneakerstop.Presentation.ui.theme.BodyUltraSmall
import com.example.sneakerstop.Presentation.ui.theme.Hint
import com.example.sneakerstop.Presentation.ui.theme.Red
import com.example.sneakerstop.Domain.viewmodel.MainViewModel

@Composable
fun ProductCard(product: Products, onChange: () -> Unit){
    val vm = MainViewModel()
    var isFavorite by remember { mutableStateOf<Boolean>(false) }
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        vm.getIsFavorite(product.id){ result ->
            isFavorite = result
        }
    }

    Column(modifier = Modifier.height(180.dp).width(160.dp).clip(RoundedCornerShape(16.dp)).background(
        Block
    ), verticalArrangement = Arrangement.SpaceBetween
    ){
        Box(modifier = Modifier.padding(8.dp).fillMaxWidth().height(80.dp)){
            if(isFavorite){
                IconButton(onClick = {
                    vm.deleteFromFavorites(product.id) {result ->
                        if (result){
                            isFavorite = false
                            onChange()
                        }
                        else{
                            Toast.makeText(context, "Не удалось убрать из избранного", Toast.LENGTH_SHORT).show()
                        }
                    }
                }, modifier = Modifier.clip(shape = RoundedCornerShape(50.dp)).size(30.dp).background(
                    Background
                ).align(Alignment.TopStart)) {
                    Icon(painterResource(R.drawable.favorite_filled_icon), "", tint= Red, modifier = Modifier.size(12.dp))
                }
            }
            else{
                IconButton(onClick = {
                    vm.insertFavorite(product.id) {result ->
                        if (result){
                            isFavorite = true
                            onChange()
                        }
                        else{
                            Toast.makeText(context, "Не удалось добавить в избранное", Toast.LENGTH_SHORT).show()
                        }
                    }
                }, modifier = Modifier.clip(shape = RoundedCornerShape(50.dp)).size(30.dp).background(
                    Background
                ).align(Alignment.TopStart)) {
                    Icon(painterResource(R.drawable.favorite_icon), "", tint= com.example.sneakerstop.Presentation.ui.theme.Text, modifier = Modifier.size(12.dp))
                }
            }
            Image(painter = rememberAsyncImagePainter(product.image), "", modifier = Modifier
                .fillMaxWidth(),
                contentScale = ContentScale.FillWidth)

        }
        Text(text="BEST SELLER".uppercase(), style= BodyUltraSmall, color = Accent, modifier = Modifier.padding(horizontal = 9.dp))
        Text(text=product.title, style= BodySmallMedium, color= Hint, modifier = Modifier.padding(horizontal = 9.dp))
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.SpaceBetween){
            Text(text="₽${product.cost}", color= com.example.sneakerstop.Presentation.ui.theme.Text, style= BodySmallInput, modifier = Modifier.padding(start = 9.dp, bottom = 8.dp))
            IconButton(onClick = {}, modifier = Modifier.clip(shape = RoundedCornerShape(16.dp, 0.dp, 16.dp, 0.dp)).size(34.dp).align(Alignment.Bottom).background(
                Accent
            )) {
                Icon(painterResource(R.drawable.add_icon), "", tint= Block)
            }
        }

    }
}