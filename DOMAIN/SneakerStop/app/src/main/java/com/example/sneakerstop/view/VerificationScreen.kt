package com.example.sneakerstop.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sneakerstop.R
import com.example.sneakerstop.ui.theme.Background
import com.example.sneakerstop.ui.theme.Block
import com.example.sneakerstop.ui.theme.BodySemiBold
import com.example.sneakerstop.ui.theme.BodySmallMedium
import com.example.sneakerstop.ui.theme.BodyUltraSmall
import com.example.sneakerstop.ui.theme.SubTextDark
import com.example.sneakerstop.ui.theme.TitleLargeSmall
import com.example.sneakerstop.viewmodel.MainViewModel
import kotlinx.coroutines.delay

@Composable
fun PinField(
    value: String,
    onValueChange: (String) -> Unit,
    focusRequester: FocusRequester
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var changingValue by remember { mutableStateOf(value) }
    Box(
        modifier = Modifier
            .width(45.dp)
            .height(99.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Background)
            .focusRequester(focusRequester),
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            value = changingValue,
            onValueChange = { newValue ->
                if (newValue.length <= 1) {
                    changingValue = newValue
                    onValueChange(newValue)
                    if (newValue.isNotEmpty()) {
                        keyboardController?.hide()
                    }
                }
            },
            textStyle = BodySemiBold.copy(
                textAlign = TextAlign.Center,
                color = com.example.sneakerstop.ui.theme.Text,
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            decorationBox = { innerTextField ->
                innerTextField()
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun VerificationScreen(navController: NavController, email: String){
    val vm = MainViewModel()
    val pinLength = 6
    val pinValues = remember { MutableList(pinLength) { "" } }
    val focusRequesters = remember { List(pinLength) { FocusRequester() } }
    val context = LocalContext.current
    var time by remember { mutableIntStateOf(60) }
    var retryText by remember { mutableStateOf("") }
    var isRunning by remember { mutableStateOf(true) }

    LaunchedEffect(isRunning) {
        while(isRunning && time > 0){
            delay(1000)
            time--
        }
        if (time == 0){
            retryText = "Отправить заново"
            isRunning = false
        }

    }

    Column(modifier= Modifier.fillMaxSize().background(Block)){
        Row(modifier = Modifier.fillMaxWidth()){
            IconButton(onClick = {navController.popBackStack()}, modifier = Modifier.padding(top=66.dp, bottom=11.dp, start = 20.dp).clip(shape = RoundedCornerShape(50.dp)).background(
                Background
            )) {
                Icon(painterResource(R.drawable.back_icon), "", tint= com.example.sneakerstop.ui.theme.Text)
            }
        }
        Column(modifier = Modifier.fillMaxWidth()){
            Text(modifier = Modifier.fillMaxWidth().padding(bottom=8.dp),text="OTP проверка", textAlign = TextAlign.Center, style= TitleLargeSmall)
            Text(modifier = Modifier.fillMaxWidth().padding(bottom=16.dp), text="Пожалуйста, проверьте свою электронную почту, чтобы увидеть код подтверждения", textAlign = TextAlign.Center, style = MaterialTheme.typography.bodySmall, color = SubTextDark)
        }
        Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)){
            Text(modifier = Modifier.fillMaxWidth().padding(bottom=20.dp),text="OTP Код", textAlign = TextAlign.Left, style= BodySmallMedium)
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            ) {
                for (i in 0 until pinLength) {
                    PinField(
                        value = pinValues[i],
                        onValueChange = { newValue ->
                            if (newValue.length <= 1) {
                                pinValues[i] = newValue
                                if (newValue.isNotEmpty() && i < pinLength - 1) {
                                    focusRequesters[i + 1].requestFocus()
                                }
                            }
                            if (pinValues.all { it.isNotEmpty() }) {
                                navController.navigate("newPasswordScreen/${email}/${pinValues.joinToString()}")
                            }
                        },
                        focusRequester = focusRequesters[i]
                    )
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                Text(text=retryText, style = BodyUltraSmall.copy(color = SubTextDark), modifier = Modifier.clickable {
                    if (time == 0){
                        retryText = ""
                        time=60
                        isRunning = true
                    }
                })
                Text(text=if (time != 0) String.format("%02d:%02d", time/60, time%60) else "", textAlign = TextAlign.Right, style = BodyUltraSmall.copy(color = SubTextDark))
            }

        }
    }
}