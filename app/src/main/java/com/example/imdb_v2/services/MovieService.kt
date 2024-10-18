import com.example.imdb_v2.model.MovieDTO
import com.example.imdb_v2.model.MoviesDTO
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


object MovieServiceConfig {

    const val BASE_URL = "https://api.themoviedb.org/3/"

    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"

    private var movieRetrofit: Retrofit? = null

    private fun getInstance(): Retrofit {
        if (movieRetrofit == null) {
            movieRetrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return movieRetrofit!!
    }

    val movieService: MovieService =
        MovieServiceConfig.getInstance().create(MovieService::class.java)
}


interface MovieService {

    @GET("movie/top_rated?api_key=c5c47722a4adcc77f6e84f28a48b857a")
    suspend fun getTopRated(): MoviesDTO

    @GET("movie/popular?api_key=c5c47722a4adcc77f6e84f28a48b857a")
    suspend fun getPopular() : MoviesDTO

    @GET("movie/{movie_id}?api_key=c5c47722a4adcc77f6e84f28a48b857a")
    suspend fun getMovie(@Path("movie_id") movieId: String) : MovieDTO
}