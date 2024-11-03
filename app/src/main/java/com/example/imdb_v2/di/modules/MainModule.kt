package com.example.imdb_v2.di.modules

import com.example.imdb_v2.domain.usecases.MovieService
import com.example.imdb_v2.view.model.mapper.MovieToMovieUIMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieServiceConfig {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"


    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideMovieService(retrofit : Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object MovieToMovieUIMapperProvider{

    @Provides
    fun provideMovieToMovieUIMapper() : MovieToMovieUIMapper {
        return MovieToMovieUIMapper()
    }
}