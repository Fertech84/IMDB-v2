package com.example.imdb_v2.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.imdb_v2.navigation.MovieScreenEnum
import com.example.imdb_v2.ui.theme.lightGray
import com.example.imdb_v2.ui.theme.mainBlack
import com.example.imdb_v2.ui.theme.mainYellow
import com.example.imdb_v2.viewmodel.BottomNavigationBarViewModel


@Composable
fun NavBar(
    bottomNavigationBarViewModel: BottomNavigationBarViewModel = BottomNavigationBarViewModel(),
    startHomeActivity: () -> Unit = {},
    startPlayActivity: () -> Unit = {},
    startProfileActivity: () -> Unit = {},
    startSearchActivity: () -> Unit = {}
) {

    val selectedScreenNameState by bottomNavigationBarViewModel.activeScreen.observeAsState()
    BottomAppBar(containerColor = mainYellow,
        modifier = Modifier.clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)),
        actions = {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = {

                    startHomeActivity()
                    bottomNavigationBarViewModel.changeActiveScreen(MovieScreenEnum.Home.name)
                }) {
                    Icon(
                        Icons.Filled.Home,
                        contentDescription = "Principal",
                        tint = if (selectedScreenNameState.equals(MovieScreenEnum.Home.name)) mainBlack else lightGray
                    )
                }
                IconButton(onClick = {
                    bottomNavigationBarViewModel.changeActiveScreen(MovieScreenEnum.Search.name)

                }) {
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = MovieScreenEnum.Search.name,
                        tint = if (selectedScreenNameState.equals(MovieScreenEnum.Search.name)) mainBlack else lightGray
                    )
                }
                IconButton(onClick = {
                    startPlayActivity()
                    bottomNavigationBarViewModel.changeActiveScreen(MovieScreenEnum.Play.name)
                }) {
                    Surface(
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Icon(
                            Icons.Filled.PlayArrow,
                            contentDescription = "Reproducir",
                            tint = mainYellow,
                            modifier = Modifier.background(
                                if (selectedScreenNameState.equals(
                                        MovieScreenEnum.Play.name
                                    )
                                ) mainBlack else lightGray
                            )
                        )
                    }
                }
                IconButton(onClick = {
                    bottomNavigationBarViewModel.changeActiveScreen(MovieScreenEnum.Profile.name)
                }) {
                    Icon(
                        Icons.Filled.Person,
                        contentDescription = "Perfil",
                        tint = if (selectedScreenNameState.equals(MovieScreenEnum.Profile.name)) mainBlack else lightGray
                    )
                }
            }
        }
    )
}