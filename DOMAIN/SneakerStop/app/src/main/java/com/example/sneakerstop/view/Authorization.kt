package com.example.sneakerstop.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sneakerstop.ui.theme.Block
import com.example.sneakerstop.ui.theme.SubTextDark
import com.example.sneakerstop.ui.theme.SubTextLight
import com.example.sneakerstop.ui.theme.TitleLargeSmall

@Preview
@Composable
fun Authorization(){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    Column(modifier=Modifier.fillMaxSize().background(Block)){
        Column(modifier = Modifier.fillMaxWidth().padding(top=121.dp)){
            Text(modifier = Modifier.fillMaxWidth(),text="Привет!", textAlign = TextAlign.Center, style= TitleLargeSmall)
            Text(modifier = Modifier.fillMaxWidth(), text="Заполните свои данные", textAlign = TextAlign.Center, style = MaterialTheme.typography.bodySmall, color = SubTextDark)
        }
        Column(modifier = Modifier.fillMaxWidth()){
            TextField(value = email, onValueChange = {email = it}, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.padding(vertical = 15.dp).clip(RoundedCornerShape(15.dp)), colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.LightGray,
                    focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent), singleLine = true)
            TextField(value = password, onValueChange = {password = it},
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.padding(vertical = 15.dp).clip(RoundedCornerShape(15.dp)), colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.LightGray,
                    focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent), singleLine = true,
                trailingIcon = {
                    if (passwordVisible){
                        Icon(painter = painterResource(R.drawable.baseline_visibility_off_24), "")
                    }
                    else{
                        Icon(painterResource(R.drawable.baseline_visibility_off_24), "")
                    }
                }
            )
        }
    }

}