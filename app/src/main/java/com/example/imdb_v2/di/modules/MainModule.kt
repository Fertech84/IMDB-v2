package com.example.imdb_v2.di.modules

import android.content.Context
import androidx.room.Room
import com.example.imdb_v2.data.local.database.DatabaseApp
import com.example.imdb_v2.data.local.database.dao.MovieDao
import com.example.imdb_v2.data.local.database.dao.UserDAO
import com.example.imdb_v2.data.repository.UserRepository
import com.example.imdb_v2.data.repository.UserRepositoryImpl
import com.example.imdb_v2.data.network.MovieService
import com.example.imdb_v2.data.repository.MovieRepository
import com.example.imdb_v2.data.repository.MovieRepositoryImpl
import com.example.imdb_v2.domain.usecases.MovieUseCases
import com.example.imdb_v2.domain.usecases.UserUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
object DatabaseProvider{

    private const val DATABASE_MAME = "main-database"

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context = context, DatabaseApp::class.java, DATABASE_MAME
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideUserDao(databaseApp : DatabaseApp) = databaseApp.getUserDao()

    @Provides
    fun provideMovieDao(databaseApp: DatabaseApp) = databaseApp.getMovieDao()


}

@Module
@InstallIn(SingletonComponent::class)
object RepositoryProvider{

    @Provides
    fun provideUserRepository(userDAO: UserDAO) : UserRepository{
        return UserRepositoryImpl(userDAO)
    }

    @Provides
    fun provideMovieRepository(movieDao: MovieDao): MovieRepository{
        return MovieRepositoryImpl(movieDao)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object UseCaseProvider{

    @Provides
    fun provideUserUseCase(userRepository: UserRepository) : UserUseCases{
        return UserUseCases(userRepository)
    }

    @Provides
    fun provideMovieUseCases(movieRepository: MovieRepository, movieService: MovieService): MovieUseCases{
        return MovieUseCases(movieService, movieRepository)
    }
}