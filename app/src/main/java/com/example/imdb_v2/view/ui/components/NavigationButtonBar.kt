package com.example.imdb_v2.view.ui.components

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.imdb_v2.view.ui.navigation.MovieScreenEnum
import com.example.imdb_v2.view.ui.theme.lightGray
import com.example.imdb_v2.view.ui.theme.mainBlack
import com.example.imdb_v2.view.ui.theme.mainYellow


@Composable
fun NavBar(
    initialState : String,
    startHomeActivity: () -> Unit = {},
    startPlayActivity: () -> Unit = {},
    startProfileActivity: () -> Unit = {},
    startSearchActivity: () -> Unit = {}
) {

    var selectedScreenNameState by rememberSaveable {
        mutableStateOf(initialState)
    }
    BottomAppBar(containerColor = mainYellow,
        modifier = Modifier.clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)),
        actions = {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = {
                    selectedScreenNameState = MovieScreenEnum.Home.name
                    startHomeActivity()
                }) {
                    Icon(
                        Icons.Filled.Home,
                        contentDescription = "Principal",
                        tint = if (selectedScreenNameState == MovieScreenEnum.Home.name) mainBlack else lightGray
                    )
                }
                IconButton(onClick = {
                    selectedScreenNameState = MovieScreenEnum.Search.name
                    startSearchActivity()
                }) {
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = MovieScreenEnum.Search.name,
                        tint = if (selectedScreenNameState == MovieScreenEnum.Search.name) mainBlack else lightGray
                    )
                }
                IconButton(onClick = {
                    selectedScreenNameState = MovieScreenEnum.Play.name
                    startPlayActivity()
                }) {
                    Surface(
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Icon(
                            Icons.Filled.PlayArrow,
                            contentDescription = "Reproducir",
                            tint = mainYellow,
                            modifier = Modifier.background(
                                if (selectedScreenNameState == MovieScreenEnum.Play.name
                                ) mainBlack else lightGray
                            )
                        )
                    }
                }
                IconButton(onClick = {
                    selectedScreenNameState = MovieScreenEnum.Profile.name
                }) {
                    Icon(
                        Icons.Filled.Person,
                        contentDescription = "Perfil",
                        tint = if (selectedScreenNameState == MovieScreenEnum.Profile.name) mainBlack else lightGray
                    )
                }
            }
        }
    )
}