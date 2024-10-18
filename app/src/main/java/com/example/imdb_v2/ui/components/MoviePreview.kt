package com.example.imdb_v2.ui.components

import MovieServiceConfig
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.imdb_v2.R
import com.example.imdb_v2.model.MovieDTO
import com.example.imdb_v2.navigation.MovieScreenEnum
import com.example.imdb_v2.ui.theme.mainWhite
import com.example.imdb_v2.viewmodel.ViewModelConfig

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MoviePreview(
    movieSource : MovieDTO,
    startPlayActivity : () -> Unit = {}
) {

    Surface (onClick = {
        ViewModelConfig.movieViewmodel.changeSelectedMovieScreen(movieSource.id.toString())
        ViewModelConfig.bottomNavigationBarViewModel.changeActiveScreen(MovieScreenEnum.Play.name)
        startPlayActivity()

    }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(mainWhite),

            )
        {
            ConstraintLayout(
                modifier = Modifier.fillMaxWidth()
            ) {
                val (trailer, poster, title, summary) = createRefs()

                GlideImage(model = MovieServiceConfig.IMAGE_BASE_URL+movieSource.trailerImage, contentDescription = ("${movieSource.movieName} trailer") ,
                    modifier = Modifier
                        .constrainAs(trailer) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .height(200.dp)
                        .fillMaxWidth()
                        .padding(0.dp),
                    contentScale = ContentScale.FillWidth

                )


                GlideImage(model = MovieServiceConfig.IMAGE_BASE_URL+movieSource.imageURL, contentDescription = ("${movieSource.movieName} poster") ,
                    modifier = Modifier
                        .constrainAs(poster) {
                            top.linkTo(trailer.bottom, margin = (-105).dp)
                            start.linkTo(trailer.start, margin = 20.dp)
                        }
                        .width(124.dp).height(200.dp),
                    contentScale = ContentScale.Fit

                )
//            Image(
//                painter = painterResource(id = R.drawable.cruella),
//                contentDescription = "Poster Image",
//                modifier = Modifier
//                    .constrainAs(poster) {
//                        top.linkTo(trailer.bottom, margin = (-105).dp)
//                        start.linkTo(trailer.start, margin = 20.dp)
//                    }
//                    .width(124.dp),
//                contentScale = ContentScale.Crop
//            )

                Text(
                    text = movieSource.movieName,
                    modifier = Modifier.constrainAs(title) {
                        top.linkTo(trailer.bottom, margin = 10.dp)
                        start.linkTo(poster.end, margin = 20.dp)
                    },
                    fontWeight = FontWeight.Bold,
                    fontSize = 21.sp

                )
                Text(
                    text = "Trailer Oficial",
                    modifier = Modifier.constrainAs(summary) {
                        top.linkTo(title.bottom, margin = 10.dp)
                        start.linkTo(title.start)
                    },
                    fontWeight = FontWeight.Normal

                )

            }
        }
    }

}

