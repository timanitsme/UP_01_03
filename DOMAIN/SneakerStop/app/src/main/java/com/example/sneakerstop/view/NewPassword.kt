package com.example.sneakerstop.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sneakerstop.R
import com.example.sneakerstop.ui.theme.Accent
import com.example.sneakerstop.ui.theme.Background
import com.example.sneakerstop.ui.theme.Block
import com.example.sneakerstop.ui.theme.BodySmallInput
import com.example.sneakerstop.ui.theme.BodySmallMedium
import com.example.sneakerstop.ui.theme.BodyUltraSmall
import com.example.sneakerstop.ui.theme.Disable
import com.example.sneakerstop.ui.theme.Hint
import com.example.sneakerstop.ui.theme.SubTextDark
import com.example.sneakerstop.ui.theme.TitleLargeSmall
import com.example.sneakerstop.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun NewPassword(navController: NavController, email: String, otp: String){
    var password by remember { mutableStateOf("") }
    var confirmedPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmedPasswordVisible by remember { mutableStateOf(false) }
    val maxLength = 50
    val context = LocalContext.current
    var buttonColor by remember { mutableStateOf(Disable) }
    val vm = MainViewModel()

    fun checkButtonColor(){
        if (confirmedPassword.length >= 6 && password.length >= 6 && confirmedPassword == password){
            buttonColor = Accent
        }
        else{
            buttonColor = Disable
        }
    }

    Column(modifier= Modifier.fillMaxSize().background(Block)){
        Row(modifier = Modifier.fillMaxWidth()){
            IconButton(onClick = {}, modifier = Modifier.padding(top=66.dp, bottom=11.dp, start = 20.dp).clip(shape = RoundedCornerShape(50.dp)).background(
                Background
            )) {
                Icon(painterResource(R.drawable.back_icon), "", tint= com.example.sneakerstop.ui.theme.Text)
            }
        }
        Column(modifier = Modifier.fillMaxWidth()){
            Text(modifier = Modifier.fillMaxWidth().padding(bottom=8.dp),text="Задать новый пароль", textAlign = TextAlign.Center, style= TitleLargeSmall)
            Text(modifier = Modifier.fillMaxWidth().padding(bottom=40.dp, start=20.dp, end=20.dp), text="Установите новый пароль для входа в учетную запись", textAlign = TextAlign.Center, style = MaterialTheme.typography.bodySmall, color = SubTextDark)
        }
        Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)){
            Text(modifier = Modifier.fillMaxWidth(),text="Пароль", textAlign = TextAlign.Left, style= BodySmallMedium)
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                value = password,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Background,
                    focusedIndicatorColor = Background,
                    focusedTextColor = com.example.sneakerstop.ui.theme.Text,
                    unfocusedTextColor = com.example.sneakerstop.ui.theme.Text,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Accent,
                    disabledPlaceholderColor = Hint,
                    focusedPlaceholderColor = Hint,
                    unfocusedPlaceholderColor = Hint
                ),
                textStyle = BodySmallInput,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = {
                        passwordVisible = !passwordVisible
                    }) {
                        if (passwordVisible) {
                            Icon(
                                painter = painterResource(id = R.drawable.visibility_on_icon),
                                contentDescription = "", tint= Hint
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = R.drawable.visibility_off_icon),
                                contentDescription = "", tint= Hint
                            )
                        }
                    }
                },
                onValueChange = {
                    if (it.length <= maxLength) password = it
                },
                placeholder = { Text("123456", style= BodySmallInput) },
                singleLine = true
            )
            Text(modifier = Modifier.fillMaxWidth(),text="Подтверждение пароля", textAlign = TextAlign.Left, style= BodySmallMedium)
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                value = confirmedPassword,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Background,
                    focusedIndicatorColor = Background,
                    focusedTextColor = com.example.sneakerstop.ui.theme.Text,
                    unfocusedTextColor = com.example.sneakerstop.ui.theme.Text,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Accent,
                    disabledPlaceholderColor = Hint,
                    focusedPlaceholderColor = Hint,
                    unfocusedPlaceholderColor = Hint
                ),
                textStyle = BodySmallInput,
                visualTransformation = if (confirmedPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = {
                        confirmedPasswordVisible = !confirmedPasswordVisible
                    }) {
                        if (confirmedPasswordVisible) {
                            Icon(
                                painter = painterResource(id = R.drawable.visibility_on_icon),
                                contentDescription = "", tint= Hint
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = R.drawable.visibility_off_icon),
                                contentDescription = "", tint= Hint
                            )
                        }
                    }
                },
                onValueChange = {
                    if (it.length <= maxLength) confirmedPassword = it
                    checkButtonColor()
                },
                placeholder = { Text("123456", style= BodySmallInput) },
                singleLine = true
            )

            Button(onClick = {
                if (password.isNotEmpty() && confirmedPassword.isNotEmpty() && password == confirmedPassword){
                    vm.updatePassword(otp, password){updateSuccess, message ->
                        if (updateSuccess){
                            Toast.makeText(context, "Пароль успешно изменен", Toast.LENGTH_SHORT).show()
                            navController.navigate("authScreen"){
                                popUpTo("newPasswordScreen"){
                                    inclusive = true
                                }
                            }
                        }
                        else{
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                else if(password != confirmedPassword){
                    Toast.makeText(context, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(context, "Заполните поля", Toast.LENGTH_SHORT).show()
                }

            }, shape= RoundedCornerShape(8.dp), modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(
                containerColor = buttonColor
            )){
                Text("Сохранить", style= BodySmallInput, color = Background, textAlign = TextAlign.Center, modifier = Modifier.padding(10.dp))
            }


        }
    }

}