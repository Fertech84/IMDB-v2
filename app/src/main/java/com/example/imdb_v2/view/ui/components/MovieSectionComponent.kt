package com.example.imdb_v2.view.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.imdb_v2.view.model.MovieUI
import com.example.imdb_v2.view.ui.theme.mainWhite
import com.example.imdb_v2.view.viewmodel.MovieViewmodel

@Composable
fun MovieSectionComponent(
    title: String = "Titulo",
    movies: List<MovieUI>,
    startPlayActivity: () -> Unit = {},
    movieViewmodel: MovieViewmodel = viewModel(),
) {
    Box(
        modifier = Modifier.background(mainWhite)

    ) {
        Column {
            Spacer(modifier = Modifier.height(20.dp))
            SectionTitle(title = title)
            Spacer(modifier = Modifier.height(10.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                item { Spacer(modifier = Modifier.width(1.dp)) }

                items(movies) {
                    MovieComponent(movieSource = it, startPlayActivity = startPlayActivity, movieViewmodel = movieViewmodel)

                }
                item { Spacer(modifier = Modifier.width(1.dp)) }

            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}


