package com.example.imdb_v2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.imdb_v2.navigation.MovieScreenEnum
import com.example.imdb_v2.navigation.NavigationWrapper
import com.example.imdb_v2.ui.components.NavBar
import com.example.imdb_v2.ui.pages.HomePage
import com.example.imdb_v2.ui.pages.MainPage
import com.example.imdb_v2.ui.pages.MovieScreen
import com.example.imdb_v2.ui.theme.IMDBv2Theme
import com.example.imdb_v2.ui.theme.mainYellow
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




