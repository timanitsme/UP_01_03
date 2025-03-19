package com.example.sneakerstop.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sneakerstop.R
import com.example.sneakerstop.ui.theme.Block
import com.example.sneakerstop.ui.theme.SubTextDark

@Preview(showBackground = true)
@Composable
fun ResetPasswordModal(isOpen: Boolean, email: String, navController: NavController){
        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xCC1B1E28))
            .clickable { navController.navigate("verificationScreen/${email}") },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        )
        {
            Column(modifier = Modifier.padding(20.dp).clip(RoundedCornerShape(16.dp)).background(Block).padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(R.drawable.check_email_icon),
                    contentDescription = "",
                    modifier = Modifier.padding(top=10.dp, bottom = 24.dp)
                )

                Text("Проверьте ваш Email", modifier = Modifier.padding(bottom = 8.dp), textAlign = TextAlign.Center, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
                Text("Мы отправили код восстановления пароля на вашу электронную почту.", textAlign = TextAlign.Center, color = SubTextDark, style = MaterialTheme.typography.bodySmall)
            }
        }

}