package com.example.sneakerstop.view

import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

@Composable
fun CircularCheckbox(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    icon: Int = R.drawable.policy_check_icon,
    size: Int = 25
) {
    Box(
        modifier = Modifier
            .size(size.dp)
            .clip(CircleShape)
            .background(Background)
            .clickable { onCheckedChange(!isChecked) },
        contentAlignment = Alignment.Center
    ) {
        if (isChecked) {
            Icon(painterResource(icon), "", tint= com.example.sneakerstop.ui.theme.Text, modifier = Modifier.size((size / 2).dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Registration(){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var policyChecked by remember { mutableStateOf(false) }
    val maxLength = 30
    val context = LocalContext.current

    Column(modifier= Modifier.fillMaxSize().background(Block)){
        Row(modifier = Modifier.fillMaxWidth()){
            IconButton(onClick = {}, modifier = Modifier.padding(top=66.dp, bottom=11.dp, start = 20.dp).clip(shape = RoundedCornerShape(50.dp)).background(
                Background
            )) {
                Icon(painterResource(R.drawable.back_icon), "", tint= com.example.sneakerstop.ui.theme.Text)
            }
        }
        Column(modifier = Modifier.fillMaxWidth()){
            Text(modifier = Modifier.fillMaxWidth(),text="Регистрация", textAlign = TextAlign.Center, style= TitleLargeSmall)
            Text(modifier = Modifier.fillMaxWidth(), text="Заполните свои данные", textAlign = TextAlign.Center, style = MaterialTheme.typography.bodySmall, color = SubTextDark)
        }
        Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)){
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
                    focusedTextColor = com.example.sneakerstop.ui.theme.Text,
                    unfocusedTextColor = com.example.sneakerstop.ui.theme.Text,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Accent,
                    disabledPlaceholderColor = Hint,
                    focusedPlaceholderColor = Hint,
                    unfocusedPlaceholderColor = Hint
                ),

                onValueChange = {
                    if (it.length <= maxLength) name = it
                    /*if (email.value.isNotEmpty() and emailPattern.matches(email.value)) {
                    colorOfButton = lavender.value
                } else {
                    colorOfButton = gainsboro.value
                }*/
                },
                placeholder = { Text("xxxxxxxx", style= BodySmallInput) },
                singleLine = true
            )
            Text(modifier = Modifier.fillMaxWidth(),text="Email", textAlign = TextAlign.Left, style= BodySmallMedium)
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top=12.dp, bottom = 30.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                textStyle = BodySmallInput,
                value = email,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
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

                onValueChange = {
                    if (it.length <= maxLength) email = it
                    /*if (email.value.isNotEmpty() and emailPattern.matches(email.value)) {
                    colorOfButton = lavender.value
                } else {
                    colorOfButton = gainsboro.value
                }*/
                },
                placeholder = { Text("xyz@gmail.com", style= BodySmallInput) },
                singleLine = true
            )
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
            Row(modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp, top = 0.dp, start = 0.dp, end = 0.dp), verticalAlignment = Alignment.CenterVertically){
                CircularCheckbox(policyChecked, {policyChecked = !policyChecked})
                TextButton(onClick = {
                }, colors = ButtonDefaults.textButtonColors(contentColor = Hint, containerColor = Color.Transparent),
                    modifier = Modifier)
                { Text("Даю согласие на обработку персональных данных", fontSize = 12.sp, style = MaterialTheme.typography.bodySmall.copy(
                    textDecoration = TextDecoration.Underline
                ), overflow = TextOverflow.Visible) }
            }
            Button(onClick = {
                if (password.isNotEmpty() && email.isNotEmpty()){
                    /*vm.signIn(email, password){success ->
                        if (success){
                            Toast.makeText(context, "Авторизация успешна", Toast.LENGTH_SHORT).show()
                            navController.navigate("homeScreen")
                        }
                        else{
                            Toast.makeText(context, "Неверные данные", Toast.LENGTH_SHORT).show()
                        }
                    }*/
                }
                else{
                    Toast.makeText(context, "Заполните поля", Toast.LENGTH_SHORT).show()
                }

            }, shape= RoundedCornerShape(8.dp), modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(
                containerColor = Disable
            )){
                Text("Зарегистрироваться", style= BodySmallInput, color = Background, textAlign = TextAlign.Center, modifier = Modifier.padding(10.dp))
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 65.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Есть аккаунт? ",
                        color = Hint,
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center
                    )
                    TextButton(
                        onClick = {
                            // navController.navigate("registrationScreen")
                        },
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = com.example.sneakerstop.ui.theme.Text,
                            containerColor = Color.Transparent
                        ),
                        modifier = Modifier.padding(0.dp)
                    ) {
                        Text(
                            text = "Войти",
                            style = MaterialTheme.typography.bodySmall,
                            color = com.example.sneakerstop.ui.theme.Text
                        )
                    }
                }
            }

        }
    }

}