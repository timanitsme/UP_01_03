package com.example.sneakerstop.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import com.example.sneakerstop.R
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

@Preview
@Composable
fun Home(){
    Column(modifier= Modifier.fillMaxSize().background(Background).padding(horizontal = 20.dp)){
        Row(modifier = Modifier.fillMaxWidth().padding(top=50.dp), horizontalArrangement = Arrangement.SpaceBetween){
            Icon(painterResource(R.drawable.menu_icon), "", modifier = Modifier.clickable {  })
            Text(text="Главная", style= TitleLargeSmall)
            Box(Modifier){
                IconButton(onClick = {}, modifier = Modifier.clip(shape = RoundedCornerShape(50.dp)).background(
                    Block)) {
                    Icon(painterResource(R.drawable.bag_icon), "", tint=com.example.sneakerstop.ui.theme.Text)
                }
                Box(Modifier.size(8.dp).clip(CircleShape).background(Red).align(Alignment.TopEnd),){}
            }
        }

    }

}