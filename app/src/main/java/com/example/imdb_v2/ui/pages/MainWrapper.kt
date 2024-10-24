package com.example.imdb_v2.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.imdb_v2.navigation.MovieScreenEnum
import com.example.imdb_v2.ui.components.NavBar
import com.example.imdb_v2.ui.theme.mainYellow
import com.example.imdb_v2.viewmodel.MovieViewmodel

@Composable
fun MainPage(
    movieViewmodel: MovieViewmodel = viewModel(),
    startHomeScreen: () -> Unit = {},
    startPlayScreen: () -> Unit = {},
    startSearchScreen: () -> Unit = {},
    startProfileScreen: () -> Unit = {},
    currentScreen: String = MovieScreenEnum.Home.name

) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavBar(
                startHomeActivity = startHomeScreen,
                startPlayActivity = startPlayScreen,
                startSearchActivity = startSearchScreen,
                initialState = currentScreen
            )
        },
    ) { innerPadding ->




            when (currentScreen) {
                MovieScreenEnum.Home.name -> HomePage(
                    movieViewModel = movieViewmodel,

                    padding = innerPadding,
                    startDetailScreen = startPlayScreen
                )

                MovieScreenEnum.Play.name -> MovieScreen(padding = innerPadding,
                    movieViewmodel = movieViewmodel,

                    )

                MovieScreenEnum.Search.name -> SearchPage(
                    movieViewmodel = movieViewmodel,
                    startPlayScreen = startPlayScreen
                )
            }




        //MovieScreen(title = "alñksjdlf", padding = innerPadding )
    }
}