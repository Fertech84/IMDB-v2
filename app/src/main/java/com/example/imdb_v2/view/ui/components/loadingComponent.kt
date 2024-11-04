package com.example.imdb_v2.view.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.imdb_v2.view.ui.theme.mainYellow

@Composable
fun LoadingBar(){
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        CircularProgressIndicator(
            trackColor = mainYellow
        )
    }
}

@Preview
@Composable
fun TestLoadingBar(){
    LoadingBar()
}