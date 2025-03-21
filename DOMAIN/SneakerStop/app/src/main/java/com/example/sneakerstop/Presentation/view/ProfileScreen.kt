package com.example.sneakerstop.Presentation.view

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.sneakerstop.R
import com.example.sneakerstop.Domain.utils.generateBarcode
import com.example.sneakerstop.Domain.model.Profiles
import com.example.sneakerstop.Presentation.ui.theme.Accent
import com.example.sneakerstop.Presentation.ui.theme.Background
import com.example.sneakerstop.Presentation.ui.theme.Black
import com.example.sneakerstop.Presentation.ui.theme.Block
import com.example.sneakerstop.Presentation.ui.theme.BodySmallInput
import com.example.sneakerstop.Presentation.ui.theme.BodySmallMedium
import com.example.sneakerstop.Presentation.ui.theme.BodySmallSemiBold
import com.example.sneakerstop.Presentation.ui.theme.BodyUltraSmall
import com.example.sneakerstop.Presentation.ui.theme.Hint
import com.example.sneakerstop.Presentation.view.Navigation.BottomNav
import com.example.sneakerstop.Domain.viewmodel.MainViewModel
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ProfileScreen(navController: NavController){ //
    var isLoading by remember { mutableStateOf(true) }
    var currentProfile by remember { mutableStateOf<Profiles?>(null) }
    val vm = MainViewModel()
    val context = LocalContext.current
    var isMenuExpanded by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    var lastname by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var editMode by remember { mutableStateOf(false) }
    val maxLength = 50
    var barcodeBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var selectedImage by remember { mutableStateOf<Uri?>(null) }

    val imagePicker = rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia(), onResult = { uri ->
        selectedImage = uri
    })

    LaunchedEffect(isLoading) {
        vm.getCurrentUserProfile {result ->
            if(result != null){
                currentProfile = result
                name = currentProfile?.firstname?:""
                lastname = currentProfile?.lastname?:""
                address = currentProfile?.address?:""
                phone = currentProfile?.phone?:""
                barcodeBitmap = generateBarcode(result.user_id)
            }
            else{
                Log.e("Profile profile", "Профиль не получен")
            }
        }
    }

    LaunchedEffect(selectedImage) {
        if (selectedImage!= null){
            vm.loadImageToBucket(selectedImage!!, context, "${UUID.randomUUID()}"){ imagePath ->
                editMode = false
                if (imagePath != null){
                    currentProfile?.photo = imagePath
                    currentProfile?.let {
                        vm.updateProfileData(it){ result ->
                            if (result){
                                Toast.makeText(context, "Изображение успешно обновлено", Toast.LENGTH_SHORT).show()
                            }
                            else{
                                Toast.makeText(context, "Ошибка при обновлении профиля", Toast.LENGTH_SHORT).show(
                                )
                            }
                        }
                    }
                }
                else{
                    Toast.makeText(context, "Ошибка при загрузке изображения", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Column(modifier= Modifier.fillMaxSize().background(Block).padding(horizontal = 20.dp)) {
        if (!editMode){
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 50.dp, bottom = 50.dp),
                horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(painterResource(R.drawable.menu_icon), "", modifier = Modifier.clickable { isMenuExpanded = !isMenuExpanded })
                Text(text = "Профиль", style = BodySmallSemiBold, color= Black)
                IconButton(
                    onClick = {},
                    modifier = Modifier.clip(shape = RoundedCornerShape(50.dp)).background(
                        Accent
                    )
                ) {
                    Icon(
                        painterResource(R.drawable.edit_icon),
                        "",
                        tint = Block,
                        modifier = Modifier.clickable { editMode = true }
                    )
                }
            }
        }
        else{
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 50.dp, bottom = 50.dp),
                horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = {
                    currentProfile?.firstname = name
                    currentProfile?.lastname = lastname
                    currentProfile?.address = address
                    currentProfile?.phone = phone
                    currentProfile?.let {
                        vm.updateProfileData(it){ result ->
                            if (result){
                                Toast.makeText(context, "Профиль успешно обновлен", Toast.LENGTH_SHORT).show()
                                editMode = false
                            } else{
                                Toast.makeText(context, "Не удалось обновить профиль", Toast.LENGTH_SHORT).show()
                            }

                        }
                    }

                }, shape= RoundedCornerShape(25.dp), modifier = Modifier.padding(horizontal = 35.dp), colors = ButtonDefaults.buttonColors(
                    containerColor = Accent
                )){
                    Text("Сохранить", style= BodySmallInput, color = Background, textAlign = TextAlign.Center, modifier = Modifier.padding(10.dp))
                }
            }
        }
        Box(modifier = Modifier.size(96.dp).clip(CircleShape).background(Background).align(Alignment.CenterHorizontally)){
            Image(painter = rememberAsyncImagePainter(currentProfile?.photo), "", modifier = Modifier
                .fillMaxWidth(), alignment = Alignment.Center,
                contentScale = ContentScale.Crop)
        }
        Text(text="${currentProfile?.firstname?:""} ${currentProfile?.lastname?:""}", style = MaterialTheme.typography.titleSmall, color = com.example.sneakerstop.Presentation.ui.theme.Text, modifier = Modifier.padding(top=8.dp).align(Alignment.CenterHorizontally))
        if (editMode) Text(text="Изменить фото профиля", style = BodyUltraSmall, color = Accent, modifier = Modifier.padding(top=8.dp).align(Alignment.CenterHorizontally).clickable {
            imagePicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))

        })
        Spacer(modifier = Modifier.height(35.dp))
        Row(modifier = Modifier.fillMaxWidth().height(65.dp).clip(RoundedCornerShape(16.dp)).background(
            Background
        ).clickable{navController.navigate("loyaltyCardScreen")}, verticalAlignment = Alignment.CenterVertically){
            Text(text="Открыть", style= BodyUltraSmall, color= com.example.sneakerstop.Presentation.ui.theme.Text, modifier = Modifier.rotate(270f).padding(end=5.dp) )
            if (barcodeBitmap != null) {
                Image(
                    bitmap = barcodeBitmap!!.asImageBitmap(),
                    contentDescription = "",
                    modifier = Modifier.size(285.dp, 50.dp).clip(RoundedCornerShape(8.dp))
                )
            }
        }

        Column(modifier = Modifier.fillMaxWidth().padding(bottom = 120.dp).verticalScroll(scrollState)) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(modifier = Modifier.fillMaxWidth(),text="Имя", textAlign = TextAlign.Left, style= BodySmallMedium)
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top=12.dp, bottom = 30.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                textStyle = BodySmallInput,
                value = name,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Background,
                    focusedIndicatorColor = Background,
                    focusedTextColor = com.example.sneakerstop.Presentation.ui.theme.Text,
                    unfocusedTextColor = com.example.sneakerstop.Presentation.ui.theme.Text,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Accent,
                    disabledPlaceholderColor = Hint,
                    focusedPlaceholderColor = Hint,
                    unfocusedPlaceholderColor = Hint
                ),
                readOnly = !editMode,
                onValueChange = {
                    if (it.length <= maxLength) name = it
                },
                placeholder = { Text("xxxxxxxx", style= BodySmallInput) },
                singleLine = true
            )
            Text(modifier = Modifier.fillMaxWidth(),text="Фамилия", textAlign = TextAlign.Left, style= BodySmallMedium)
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top=12.dp, bottom = 30.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                textStyle = BodySmallInput,
                value = lastname,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Background,
                    focusedIndicatorColor = Background,
                    focusedTextColor = com.example.sneakerstop.Presentation.ui.theme.Text,
                    unfocusedTextColor = com.example.sneakerstop.Presentation.ui.theme.Text,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Accent,
                    disabledPlaceholderColor = Hint,
                    focusedPlaceholderColor = Hint,
                    unfocusedPlaceholderColor = Hint
                ),
                readOnly = !editMode,
                onValueChange = {
                    if (it.length <= maxLength) lastname = it
                },
                placeholder = { Text("xxxxxxxx", style= BodySmallInput) },
                singleLine = true
            )
            Text(modifier = Modifier.fillMaxWidth(),text="Адрес", textAlign = TextAlign.Left, style= BodySmallMedium)
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top=12.dp, bottom = 30.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                textStyle = BodySmallInput,
                value = address,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Background,
                    focusedIndicatorColor = Background,
                    focusedTextColor = com.example.sneakerstop.Presentation.ui.theme.Text,
                    unfocusedTextColor = com.example.sneakerstop.Presentation.ui.theme.Text,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Accent,
                    disabledPlaceholderColor = Hint,
                    focusedPlaceholderColor = Hint,
                    unfocusedPlaceholderColor = Hint
                ),
                readOnly = !editMode,
                onValueChange = {
                    if (it.length <= maxLength) address = it
                },
                placeholder = { Text("xxxxxxxx", style= BodySmallInput) },
                singleLine = true
            )
            Text(modifier = Modifier.fillMaxWidth(),text="Телефон", textAlign = TextAlign.Left, style= BodySmallMedium)
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top=12.dp, bottom = 30.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                textStyle = BodySmallInput,
                value = phone,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Background,
                    focusedIndicatorColor = Background,
                    focusedTextColor = com.example.sneakerstop.Presentation.ui.theme.Text,
                    unfocusedTextColor = com.example.sneakerstop.Presentation.ui.theme.Text,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Accent,
                    disabledPlaceholderColor = Hint,
                    focusedPlaceholderColor = Hint,
                    unfocusedPlaceholderColor = Hint
                ),
                readOnly = !editMode,
                onValueChange = {
                    if (it.length <= maxLength) phone = it
                },
                placeholder = { Text("xxxxxxxx", style= BodySmallInput) },
                singleLine = true
            )
        }



    }
    BottomNav("profileScreen",navController = navController)
    AnimatedVisibility(
        visible = isMenuExpanded,
        enter = slideInHorizontally(initialOffsetX = { -it }) + fadeIn(),
        exit = slideOutHorizontally(targetOffsetX = { -it }) + fadeOut()
    ) {
        SideMenu(navController, onClose = { isMenuExpanded = false })
    }
}