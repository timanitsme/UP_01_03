package com.example.sneakerstop.view

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.sneakerstop.R
import com.example.sneakerstop.model.Actions
import com.example.sneakerstop.model.Categories
import com.example.sneakerstop.model.Products
import com.example.sneakerstop.ui.theme.Accent
import com.example.sneakerstop.ui.theme.Background
import com.example.sneakerstop.ui.theme.Block
import com.example.sneakerstop.ui.theme.BodySmallInput
import com.example.sneakerstop.ui.theme.BodySmallMedium
import com.example.sneakerstop.ui.theme.BodyUltraSmall
import com.example.sneakerstop.ui.theme.Hint
import com.example.sneakerstop.ui.theme.Red
import com.example.sneakerstop.ui.theme.SubTextDark
import com.example.sneakerstop.ui.theme.TitleLargeSmall
import com.example.sneakerstop.view.Navigation.BottomNav
import com.example.sneakerstop.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Home(navController: NavController){
    var isLoading by remember { mutableStateOf(true) }
    var search by remember { mutableStateOf("") }
    var categories by remember { mutableStateOf(listOf<Categories>()) }
    var products by remember { mutableStateOf(listOf<Products>()) }
    var actions by remember { mutableStateOf(listOf<Actions>()) }
    val vm = MainViewModel()
    var isMenuExpanded by remember { mutableStateOf(false) }



    LaunchedEffect(isLoading){
        if (isLoading){
            vm.getCategories { result ->
                if (result != null){
                    categories = result
                }
                else{
                    Log.e("Home categories", "Категории не получены")
                }
            }
            vm.getProducts { result ->
                if (result != null){
                    products = result
                }
                else{
                    Log.e("Home products", "Продукты не получены")
                }
            }
            vm.getActions { result ->
                if (result != null){
                    actions = result
                }
                else{
                    Log.e("Home actions", "Акции не получены")
                }
            }
            isLoading = false
        }
    }

    Column(modifier= Modifier.fillMaxSize().background(Background).padding(horizontal = 20.dp)){
        Row(modifier = Modifier.fillMaxWidth().padding(top=50.dp), horizontalArrangement = Arrangement.SpaceBetween){
            Icon(painterResource(R.drawable.menu_icon), "", modifier = Modifier.clickable { isMenuExpanded = !isMenuExpanded })
            Text(text="Главная", style= TitleLargeSmall)
            Box(Modifier){
                IconButton(onClick = {}, modifier = Modifier.clip(shape = RoundedCornerShape(50.dp)).background(
                    Block)) {
                    Icon(painterResource(R.drawable.bag_icon), "", tint=com.example.sneakerstop.ui.theme.Text)
                }
                Box(Modifier.size(8.dp).clip(CircleShape).background(Red).align(Alignment.TopEnd),){}
            }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(top=20.dp), horizontalArrangement = Arrangement.SpaceBetween){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(14.dp))
                    .background(Block)
                    .weight(4f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.search_icon),
                    contentDescription = "",
                    tint = Hint,
                    modifier = Modifier.padding(start = 12.dp)
                )

                TextField(
                    value = search,
                    onValueChange = { search = it },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Accent,
                        focusedTextColor = com.example.sneakerstop.ui.theme.Text,
                        unfocusedTextColor = com.example.sneakerstop.ui.theme.Text,
                        disabledPlaceholderColor = Hint,
                        focusedPlaceholderColor = Hint,
                        unfocusedPlaceholderColor = Hint
                    ),
                    textStyle = BodySmallInput,
                    placeholder = { Text("Поиск", style = BodySmallInput) },
                    singleLine = true
                )
            }
            Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.End){
                IconButton(onClick = {}, modifier = Modifier.clip(shape = RoundedCornerShape(50.dp)).size(52.dp).background(
                    Accent)) {
                    Icon(painterResource(R.drawable.sliders_icon), "", tint=Block)
                }
            }

        }

        Column(modifier = Modifier.fillMaxWidth().padding(vertical = 23.dp)) {
            Text(modifier = Modifier.fillMaxWidth(),text="Категории", textAlign = TextAlign.Left, style= BodySmallMedium, color= com.example.sneakerstop.ui.theme.Text)
            LazyRow(modifier = Modifier.fillMaxWidth().padding(top = 15.dp)){
                items(categories){category ->
                    Row(modifier = Modifier.padding(end=16.dp).clip(RoundedCornerShape(8.dp)).background(Block).clickable { navController.navigate("catalog/${category.id}") }) {
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

        Column(modifier = Modifier.fillMaxWidth().padding(vertical = 23.dp)) {
            Row(modifier = Modifier.fillMaxWidth().padding(bottom = 30.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom){
                Text(text="Популярное", style= BodySmallMedium, color= com.example.sneakerstop.ui.theme.Text)
                Text(modifier = Modifier.clickable {  },text="Все", style= BodyUltraSmall, color= Accent)
            }
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(products.take(2)){product ->
                    ProductCard(product, onChange = {isLoading = true})
                    Spacer(modifier = Modifier.width(15.dp))
                }

            }
        }
        if (actions.isNotEmpty()){
            Column(modifier = Modifier.fillMaxWidth().padding(vertical = 23.dp)) {
                Row(modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom){
                    Text(text="Акции", style= BodySmallMedium, color= com.example.sneakerstop.ui.theme.Text)
                    Text(modifier = Modifier.clickable {  }, text="Все", style= BodyUltraSmall, color= Accent)
                }
                Box(Modifier.height(95.dp).fillMaxWidth(), contentAlignment = Alignment.Center){
                    Image(painter = rememberAsyncImagePainter(actions[0].photo), "", modifier = Modifier
                        .fillMaxWidth().height(95.dp),
                        contentScale = ContentScale.FillWidth)
                }


            }
        }


    }
    BottomNav(navController = navController)
    AnimatedVisibility(
        visible = isMenuExpanded,
        enter = slideInHorizontally(initialOffsetX = { -it }) + fadeIn(),
        exit = slideOutHorizontally(targetOffsetX = { -it }) + fadeOut()
    ) {
        SideMenu(navController, onClose = { isMenuExpanded = false })
    }

}