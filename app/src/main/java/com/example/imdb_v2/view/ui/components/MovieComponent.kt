package com.example.imdb_v2.view.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil3.compose.AsyncImage
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.imdb_v2.di.modules.MovieServiceConfig
import com.example.imdb_v2.view.model.MovieUI
import com.example.imdb_v2.view.ui.theme.mainBlack
import com.example.imdb_v2.view.ui.theme.mainWhite
import com.example.imdb_v2.view.ui.theme.mainYellow
import com.example.imdb_v2.view.ui.theme.robotoFamily
import com.example.imdb_v2.view.viewmodel.MovieViewmodel


@Composable
fun MovieComponent(
    movieSource: MovieUI, movieViewmodel: MovieViewmodel,
    startPlayActivity : () -> Unit = {}
) {
    Surface(
        shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp),
        modifier = Modifier.shadow(
            elevation = 2.dp,
            shape = RoundedCornerShape(bottomStart = 6.dp, bottomEnd = 6.dp)
        ), onClick = {

            movieViewmodel.changeSelectedMovieScreen(movieSource.id.toString())
            startPlayActivity()
        }
    ) {
        Box(
            modifier = Modifier
                .background(mainWhite)
                .width(100.dp)


        ) {
            ConstraintLayout {
                val (poster, addButton, star, rating, title, dangerIcon) = createRefs()

                SubcomposeAsyncImage(
                    model = MovieServiceConfig.IMAGE_BASE_URL + movieSource.imageURL,
                    contentDescription = (movieSource.movieName + " poster"),
                    modifier = Modifier
                        .constrainAs(poster) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .width(110.dp)
                        .height(140.dp),
                    contentScale = ContentScale.Fit,
                    loading = {
                        Column (
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                            ) {
                            CircularProgressIndicator(
                                color = mainYellow
                            )
                        }
                    }

                )


                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Add to watch list",
                    modifier = Modifier.constrainAs(addButton) {
                        top.linkTo(poster.top, margin = 2.dp)
                        start.linkTo(poster.start, margin = 2.dp)
                    },
                    tint = mainWhite

                )
                Surface(
                    modifier = Modifier.constrainAs(star) {
                        top.linkTo(poster.bottom, margin = 10.dp)
                        start.linkTo(poster.start, margin = 10.dp)
                    },
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Icon(
                        Icons.Rounded.Star, contentDescription = "rating icon",
                        modifier = Modifier
                            .background(mainYellow)
                            .width(12.3.dp),
                        tint = mainWhite
                    )
                }

                Text(text = movieSource.rating, fontFamily = robotoFamily,
                    fontWeight = FontWeight.Thin,
                    color = mainBlack,
                    fontSize = 10.sp,
                    modifier = Modifier.constrainAs(rating) {
                        top.linkTo(star.top, margin = (-5).dp)
                        start.linkTo(star.end, margin = 5.dp)
                    }
                )


                var movieTitle = movieSource.movieName
                if (movieTitle.length > 10) {
                    movieTitle = movieTitle.substring(0, 10) + "..."
                }
                Text(

                    text = movieTitle,
                    modifier = Modifier.constrainAs(title) {
                        top.linkTo(star.bottom, margin = 5.dp)
                        start.linkTo(star.start)
                    },
                    fontSize = 10.sp, fontWeight = FontWeight.Normal,

                    )

                Icon(
                    Icons.Outlined.Info, contentDescription = "More information",
                    modifier = Modifier
                        .constrainAs(dangerIcon) {
                            top.linkTo(title.bottom, margin = 3.dp)
                            end.linkTo(parent.end, margin = 5.dp)
                            bottom.linkTo(parent.bottom, margin = 10.dp)
                        }
                        .width(13.5.dp)
                )

            }
        }
    }
}
