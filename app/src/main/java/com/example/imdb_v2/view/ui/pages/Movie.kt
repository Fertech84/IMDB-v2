package com.example.imdb_v2.view.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.imdb_v2.R
import com.example.imdb_v2.di.modules.MovieServiceConfig
import com.example.imdb_v2.view.ui.components.SectionTitle
import com.example.imdb_v2.view.ui.theme.lightGray
import com.example.imdb_v2.view.ui.theme.mainBlack
import com.example.imdb_v2.view.ui.theme.mainWhite
import com.example.imdb_v2.view.ui.theme.mainYellow
import com.example.imdb_v2.view.viewmodel.MovieViewmodel


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieScreen(padding: PaddingValues,
                movieViewmodel: MovieViewmodel = viewModel(),
                startHomeScreen : () -> Unit = {},
                startProfileScreen : () -> Unit = {},
                startSearchScreen : () -> Unit = {}
                 ) {

    val movieToShowInScreen by movieViewmodel.selectedMovieScreen.observeAsState()

    if (movieToShowInScreen != null){
        LazyColumn (modifier = Modifier.padding(padding)) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.White)
                ) {
                    Column(
                        modifier = Modifier.border(
                            width = 0.5.dp, color = lightGray
                        )
                    ) {
                        Spacer(modifier = Modifier.height(20.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {

                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Volver",
                                modifier = Modifier.padding(start = 10.dp)
                            )
                            Text(text = movieToShowInScreen!!.movieName)
                            Spacer(modifier = Modifier.width(30.dp))
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    SectionTitle(title = movieToShowInScreen!!.movieName)
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        Spacer(modifier = Modifier.width(35.dp))
                        Text(
                            text = movieToShowInScreen!!.movieName,

                            fontWeight = FontWeight.Normal,
                            color = lightGray,
                            fontSize = 10.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        Spacer(modifier = Modifier.width(35.dp))
                        Text(
                            text = "Miniserie de TV 2020 - 2020 16",

                            fontWeight = FontWeight.Normal,
                            color = lightGray,
                            fontSize = 12.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    GlideImage(model = (MovieServiceConfig.IMAGE_BASE_URL+movieToShowInScreen!!.trailerImage), contentDescription = (movieToShowInScreen!!.movieName+" Trailer") ,
                        modifier = Modifier.fillMaxWidth()
                        ,
                        contentScale = ContentScale.Crop,

                    )
//                    Image(
//                        painter = painterResource(id = R.drawable.imagenfondo),
//                        contentDescription = "Principal Poster",
//                        contentScale = ContentScale.Crop,
//                        modifier = Modifier.fillMaxWidth()
//                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    Row {
                        Spacer(modifier = Modifier.width(25.dp))
                        GlideImage(model = (MovieServiceConfig.IMAGE_BASE_URL+movieToShowInScreen!!.imageURL), contentDescription = (movieToShowInScreen!!.movieName+" poster") ,
                            modifier = Modifier.width(74.dp)
                            ,
                            contentScale = ContentScale.Crop,

                            )
//                        Image(
//                            painter = painterResource(id = R.drawable.portada),
//                            contentDescription = "Poster Image",
//                            contentScale = ContentScale.Crop,
//                            modifier = Modifier.width(74.dp)
//                        )
                        Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
                            Row {
                                OutlinedCard(onClick = { /*TODO*/ }) {
                                    Text(
                                        text = "Drama",
                                        color = lightGray,
                                        modifier = Modifier.padding(10.dp)
                                    )
                                }
                                Icon(
                                    Icons.Rounded.Star,
                                    contentDescription = "estrella",
                                    tint = mainYellow,
                                    modifier = Modifier.padding(
                                        top = 10.dp,
                                        start = 10.dp
                                    )
                                )
                                Text(
                                    text = "5.0",
                                    color = lightGray,
                                    fontSize = 12.sp,
                                    modifier = Modifier.padding(top = 17.dp)
                                )
                            }
                            Text(
                                text = movieToShowInScreen!!.description,
                                fontSize = 14.sp
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .border(
                                width = 0.5.dp,
                                color = lightGray
                            )
                            .fillMaxWidth()
                            .height(40.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Guía de episodios",
                            modifier = Modifier.padding(start = 25.dp),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "7 Episodios",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = lightGray,
                            modifier = Modifier.padding(end = 30.dp)
                        )
                    }


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {

                        Button(
                            onClick = { /*TODO*/ },
                            colors = ButtonColors(
                                containerColor = mainYellow,
                                contentColor = mainBlack,
                                disabledContentColor = mainWhite,
                                disabledContainerColor = lightGray
                            )
                        ) {
                            Text(
                                text = "Agregar a mi lista de seguimiento",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(
                                    top = 10.dp,
                                    bottom = 10.dp
                                )
                            )

                        }
                    }


                }
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }

            item {
                Column(
                    modifier = Modifier
                        .background(color = Color.White)

                ) {
                    SectionTitle(title = "Reparto")
                    Text(
                        text = "Reparto principal de la serie",
                        modifier = Modifier.padding(
                            start = 20.dp,
                            top = 10.dp
                        )
                    )


                    LazyRow(
                        modifier = Modifier.padding(top = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        item {
                            Spacer(modifier = Modifier.width(1.dp))
                        }

                        items(5){
                            Column() {
                                Image(
                                    painter = painterResource(id = R.drawable.personaje),
                                    contentDescription = "Personaje",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.width(74.dp)
                                )
                                Text(text = "Anya Taylor-Joy",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Thin
                                )
                                Text(text = "Beth Harmon",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Thin,
                                    color = lightGray
                                )
                            }
                        }


                    }

                }
            }
        }
    }else {


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(color = mainYellow)
            }
        }
    }

}


