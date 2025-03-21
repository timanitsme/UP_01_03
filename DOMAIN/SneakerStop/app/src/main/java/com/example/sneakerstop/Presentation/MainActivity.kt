package com.example.sneakerstop.Presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import com.example.sneakerstop.Presentation.ui.theme.SneakerStopTheme
import com.example.sneakerstop.Presentation.view.Navigation.Navigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SneakerStopTheme {
                //val vm:MainViewModel = viewModel()
                //var startScreen by remember { mutableStateOf("") }
                val startScreen = "splashScreen"
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
