package com.example.sneakerstop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sneakerstop.ui.theme.SneakerStopTheme
import com.example.sneakerstop.view.Navigation.Navigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SneakerStopTheme {
                //val vm:MainViewModel = viewModel()
                //var startScreen by remember { mutableStateOf("") }
                val startScreen = "homeScreen"
                LaunchedEffect(Unit)
                {
                    /*vm.is_Logged { success ->
                        if (true){
                            startScreen = "homeScreen"
                        }
                        else{
                            startScreen = "authScreen"
                        }

                    }*/

                }
                Navigation(startScreen)
            }
        }
    }
}
