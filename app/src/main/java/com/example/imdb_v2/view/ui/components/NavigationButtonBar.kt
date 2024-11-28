package com.example.imdb_v2.view.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.imdb_v2.view.ui.navigation.MovieScreenEnum
import com.example.imdb_v2.view.ui.theme.mainYellow


@Composable
fun NavBar(
    initialState: String,
    startHomeActivity: () -> Unit = {},
    startPlayActivity: () -> Unit = {},
    startProfileActivity: () -> Unit = {},
    startSearchActivity: () -> Unit = {}
) {



    NavigationBar(
        containerColor = mainYellow,
        contentColor = Color.Black,
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp))
    ) {
        NavigationBarItem(
            selected = initialState == MovieScreenEnum.Home.name,
            onClick = startHomeActivity,
            icon = {
                Icon(Icons.Filled.Home, contentDescription = "")
            },
            label = { Text(text = "Home") }
        )

        NavigationBarItem(
            selected = initialState == MovieScreenEnum.Search.name,
            onClick = startSearchActivity,
            icon = {
                Icon(Icons.Filled.Search, contentDescription = "")
            },
            label = { Text(text = "Search") }
        )

        NavigationBarItem(
            selected = initialState == MovieScreenEnum.Play.name,
            onClick = startPlayActivity,
            icon = {
                Icon(Icons.Filled.PlayCircleFilled, contentDescription = "")
            },
            label = { Text(text = "Detail") }
        )

        NavigationBarItem(
            selected = initialState == MovieScreenEnum.Profile.name,
            onClick = { },
            icon = {
                Icon(Icons.Filled.PersonPin, contentDescription = "Profile")
            },
            label = { Text(text = "Home") }
        )
    }

}

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)