package com.example.imdb_v2.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.imdb_v2.model.MovieDTO
import com.example.imdb_v2.services.MovieServiceConfig
import com.example.imdb_v2.ui.theme.lightGray
import com.example.imdb_v2.ui.theme.mainWhite
import com.example.imdb_v2.ui.theme.mainYellow
import com.example.imdb_v2.viewmodel.MovieViewmodel


@Composable
fun SearchPage(
    movieViewmodel: MovieViewmodel = viewModel(),
    startPlayScreen : ()-> Unit = {}
    ) {

    var searchMovieTextFieldState by rememberSaveable { mutableStateOf("") }
    val resultMoviesState by movieViewmodel.searchMovieList.observeAsState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = searchMovieTextFieldState,
            label = { Text(text = "Buscar en IMDb") },
            leadingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Filled.Search, contentDescription = "Search")
                }
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .width(314.dp),
            onValueChange = {
                searchMovieTextFieldState = it
                movieViewmodel.searchMovie(searchMovieTextFieldState)
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (resultMoviesState.isNullOrEmpty()) {
                item {
                    Text(text = "Aquí aparecerán los resultados")
                }
            } else {
                items(resultMoviesState!!) {
                    SearchResultComponent(
                        movieSource = it,
                        movieViewmodel = movieViewmodel,
                        startPlayScreen = startPlayScreen
                        )
                }
            }

        }


    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun SearchResultComponent(movieSource: MovieDTO, movieViewmodel: MovieViewmodel = viewModel(), startPlayScreen: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        onClick = {
            movieViewmodel.changeSelectedMovieScreen(movieSource.id.toString())
            startPlayScreen()
        },
        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
    ) {
        Row(
            modifier = Modifier
                .width(314.dp)
                .background(mainWhite)
        ) {
            GlideImage(
                model = MovieServiceConfig.IMAGE_BASE_URL + movieSource.imageURL,
                contentDescription = "Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(74.dp)
                    .height(115.dp)
            )
            Spacer(modifier = Modifier.width(15.dp))
            Column {
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = movieSource.movieName, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = movieSource.date, fontSize = 16.sp, color = lightGray)
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Text(text = movieSource.rating, fontSize = 14.sp, color = lightGray)
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(Icons.Rounded.Star, contentDescription = "rating icon", tint = mainYellow)
                }
                Spacer(modifier = Modifier.height(9.dp))
            }
        }
    }
}


@Preview
@Composable
fun PreviewSearchScreen() {
    SearchPage()
}