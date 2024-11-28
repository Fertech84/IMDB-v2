package com.example.imdb_v2.view.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.imdb_v2.view.ui.pages.LoginPage
import com.example.imdb_v2.view.ui.pages.MainPage
import com.example.imdb_v2.view.ui.pages.SignupScreen
import com.example.imdb_v2.view.viewmodel.LoginViewModel
import com.example.imdb_v2.view.viewmodel.MovieViewmodel
import com.example.imdb_v2.view.viewmodel.SignupViewModel

enum class MovieScreenEnum {
    Home,
    Play,
    Search,
    Profile,
    Login,
    Signup
}


@Composable
fun NavigationWrapper(
    movieViewmodel: MovieViewmodel = viewModel(),
    signupViewModel: SignupViewModel = viewModel(),
    loginViewModel: LoginViewModel = viewModel()
) {
    Scaffold() { innerPadding ->

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
                    navigateToHome = {screenNavigator.startSpecificScreen(MovieScreenEnum.Home.name)},
                    loginViewModel = loginViewModel
                )
            }
            composable(route = MovieScreenEnum.Signup.name){
                SignupScreen(
                    navigateToLogin = {screenNavigator.startSpecificScreen(MovieScreenEnum.Login.name)},
                    signupViewModel = signupViewModel
                )
            }

            composable(route = MovieScreenEnum.Search.name){
                MainPage(
                    movieViewmodel = movieViewmodel,
                    currentScreen = MovieScreenEnum.Search.name,
                    startHomeScreen = { screenNavigator.startSpecificScreen(MovieScreenEnum.Home.name) },
                    startPlayScreen = { screenNavigator.startSpecificScreen(MovieScreenEnum.Play.name) },
                    startSearchScreen = {screenNavigator.startSpecificScreen(MovieScreenEnum.Search.name)},
                )
            }
            composable(route = MovieScreenEnum.Home.name) {
                MainPage(
                    movieViewmodel = movieViewmodel,
                    currentScreen = MovieScreenEnum.Home.name,
                    startHomeScreen = { screenNavigator.startSpecificScreen(MovieScreenEnum.Home.name) },
                    startPlayScreen = { screenNavigator.startSpecificScreen(MovieScreenEnum.Play.name) },
                    startSearchScreen = {screenNavigator.startSpecificScreen(MovieScreenEnum.Search.name)},

                )
            }

            composable(route = MovieScreenEnum.Play.name) {

                MainPage(
                    movieViewmodel = movieViewmodel,
                    currentScreen = MovieScreenEnum.Play.name,
                    startHomeScreen = { screenNavigator.startSpecificScreen(MovieScreenEnum.Home.name) },
                    startPlayScreen = { screenNavigator.startSpecificScreen(MovieScreenEnum.Play.name) },
                    startSearchScreen = {screenNavigator.startSpecificScreen(MovieScreenEnum.Search.name)},

                )
            }


        }
    }
}


class MovieScreenNavigator(
    private val navController: NavHostController
) {
    fun startSpecificScreen(
        screen: String
    ) {
        navController.navigate(screen)
    }
}



