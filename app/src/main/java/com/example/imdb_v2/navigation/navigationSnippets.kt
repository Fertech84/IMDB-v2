package com.example.imdb_v2.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.imdb_v2.ui.pages.LoginPage
import com.example.imdb_v2.ui.pages.MainPage
import com.example.imdb_v2.ui.pages.SignupScreen
import com.example.imdb_v2.viewmodel.BottomNavigationBarViewModel
import com.example.imdb_v2.viewmodel.MovieViewmodel

enum class MovieScreenEnum() {
    Home,
    Play,
    Search,
    Profile,
    Login,
    Signup
}


@Composable
fun NavigationWrapper(
    navViewModel: BottomNavigationBarViewModel = viewModel(),
    movieViewmodel: MovieViewmodel = viewModel()
) {
    Scaffold { innerPadding ->

        val navController = rememberNavController()

        val screenNavigator = MovieScreenNavigator(navController = navController)

        NavHost(
            navController = navController,
            startDestination = MovieScreenEnum.Login.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = MovieScreenEnum.Login.name){
                LoginPage(
                    navigateToSignup = {screenNavigator.startSpecificScreen(MovieScreenEnum.Signup.name)},
                    navigateToHome = {screenNavigator.startSpecificScreen(MovieScreenEnum.Home.name)}
                )
            }
            composable(route = MovieScreenEnum.Signup.name){
                SignupScreen(
                    navigateToLogin = {screenNavigator.startSpecificScreen(MovieScreenEnum.Login.name)}
                )
            }

            composable(route = MovieScreenEnum.Home.name) {
                MainPage(
                    movieViewmodel = movieViewmodel,
                    padding = innerPadding,
                    currentScreen = MovieScreenEnum.Home.name,
                    startHomeScreen = { screenNavigator.startSpecificScreen(MovieScreenEnum.Home.name) },
                    startPlayScreen = { screenNavigator.startSpecificScreen(MovieScreenEnum.Play.name) },
                    bottomNavigationBarViewModel = navViewModel
                )
            }

            composable(route = MovieScreenEnum.Play.name) {

                MainPage(
                    padding = innerPadding,
                    movieViewmodel = movieViewmodel,
                    currentScreen = MovieScreenEnum.Play.name,
                    startHomeScreen = { screenNavigator.startSpecificScreen(MovieScreenEnum.Home.name) },
                    startPlayScreen = { screenNavigator.startSpecificScreen(MovieScreenEnum.Play.name) },
                    bottomNavigationBarViewModel = navViewModel
                )
            }


        }
    }
}


class MovieScreenNavigator(
    private val navViewModel: BottomNavigationBarViewModel = BottomNavigationBarViewModel(),
    private val navController: NavHostController
) {
    fun startSpecificScreen(
        screen: String
    ) {
        navViewModel.changeActiveScreen(screen)
        navController.navigate(screen)
    }
}



