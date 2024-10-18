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
import com.example.imdb_v2.navigation.MovieScreenEnum
import com.example.imdb_v2.ui.components.NavBar
import com.example.imdb_v2.ui.theme.mainYellow
import com.example.imdb_v2.viewmodel.BottomNavigationBarViewModel

@Composable
fun MainPage(padding : PaddingValues ,bottomNavigationBarViewModel: BottomNavigationBarViewModel = BottomNavigationBarViewModel(),
             startHomeScreen : () -> Unit = {},
             startPlayScreen : () -> Unit = {},
             startSearchScreen : () -> Unit = {},
             startProfileScreen : () -> Unit = {},
             currentScreen : String = MovieScreenEnum.Home.name

) {
    val currentScreenState by bottomNavigationBarViewModel.activeScreen.observeAsState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { NavBar(
            startHomeActivity = startHomeScreen,
            startPlayActivity = startPlayScreen,
            bottomNavigationBarViewModel = bottomNavigationBarViewModel
        ) },
    ) { innerPadding ->



        if (currentScreenState != null){
            
            when (currentScreen){
                MovieScreenEnum.Home.name-> HomePage(padding = innerPadding, startDetailScreen = startPlayScreen)
                MovieScreenEnum.Play.name-> MovieScreen(padding = innerPadding)
            }
            
            

        }else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator( color = mainYellow)
            }
        }

        //MovieScreen(title = "alñksjdlf", padding = innerPadding )
    }
}