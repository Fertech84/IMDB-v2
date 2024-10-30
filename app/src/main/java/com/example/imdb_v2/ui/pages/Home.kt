package com.example.imdb_v2.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.imdb_v2.ui.components.MoviePreview
import com.example.imdb_v2.ui.components.MovieSectionComponent
import com.example.imdb_v2.ui.theme.mainYellow
import com.example.imdb_v2.viewmodel.MovieViewmodel

@Composable
fun HomePage(
    padding: PaddingValues,
    movieViewModel: MovieViewmodel = viewModel(),
    startDetailScreen: () -> Unit = {},
    startProfileScreen: () -> Unit = {},
    startSearchScreen: () -> Unit = {}
) {

    val topRatedState by movieViewModel.topRatedMovieList.observeAsState()
    val popularState by movieViewModel.popularMovieList.observeAsState()
    val previewMovieItem by movieViewModel.previewMovieItem.observeAsState()




    if (topRatedState != null && popularState != null && previewMovieItem != null) {
        LazyColumn(
            modifier = Modifier.padding(padding),

            verticalArrangement = Arrangement.spacedBy(20.dp),

            ) {

            item {
                MoviePreview(
                    movieSource = previewMovieItem!!,
                    startPlayActivity = startDetailScreen,
                    movieViewmodel = movieViewModel,

                )
            }



            topRatedState?.let {
                item {
                    MovieSectionComponent(
                        movies = topRatedState!!,
                        title = "Top Rated",
                        startPlayActivity = startDetailScreen,
                        movieViewmodel = movieViewModel,

                    )
                }
            }

            if (!popularState.isNullOrEmpty()) {
                item {
                    MovieSectionComponent(
                        movies = popularState!!,
                        title = "Popular",
                        startPlayActivity = startDetailScreen,
                        movieViewmodel = movieViewModel,

                    )
                }
            }


        }
    } else {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                color = mainYellow
            )
        }
    }
}












