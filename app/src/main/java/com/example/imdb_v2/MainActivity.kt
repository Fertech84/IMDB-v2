package com.example.imdb_v2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.imdb_v2.view.ui.components.PrimaryButton
import com.example.imdb_v2.view.ui.navigation.NavigationWrapper
import com.example.imdb_v2.view.ui.theme.IMDBv2Theme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IMDBv2Theme {
                NavigationWrapper()

            }
        }
    }


    @Preview
    @Composable
    fun ButtonPreview() {


        PrimaryButton(buttonText = "Hello world") {
            println("Hello preview")
        }
    }


}




