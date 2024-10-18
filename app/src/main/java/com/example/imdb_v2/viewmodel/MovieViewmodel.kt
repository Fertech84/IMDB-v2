package com.example.imdb_v2.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdb_v2.model.MovieDTO
import com.example.imdb_v2.model.MoviesDTO
import com.example.imdb_v2.repository.MovieRepository
import com.example.imdb_v2.repository.MovieRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieViewmodel(
    private val movieRepository: MovieRepository = MovieRepositoryImpl()
) : ViewModel() {

    //Top rated state
    private val _topRatedMovieList = MutableLiveData<List<MovieDTO>>()
    val topRatedMovieList: LiveData<List<MovieDTO>> get() = _topRatedMovieList

    //Popular state
    private val _popularMovieList = MutableLiveData<List<MovieDTO>>()
    val popularMovieList: LiveData<List<MovieDTO>> get() = _popularMovieList

    private val _previewMovieItem = MutableLiveData<MovieDTO>()
    val previewMovieItem : LiveData<MovieDTO> get() = _previewMovieItem

    private val _selectedMovieScreen = MutableLiveData<MovieDTO>()
    val selectedMovieScreen : LiveData<MovieDTO> get() = _selectedMovieScreen



    fun getTopRated() {
        if (topRatedMovieList.value == null) {
            viewModelScope.launch(Dispatchers.IO) {
                val topRatedMovieList: MoviesDTO = movieRepository.getTopRated()

                withContext(Dispatchers.Main) {
                    _topRatedMovieList.value = topRatedMovieList.results
                    _previewMovieItem.value = topRatedMovieList.results[0]
                }
            }
        }
    }

    fun changeSelectedMovieScreen(id : String){
        viewModelScope.launch(Dispatchers.IO){
            val newSelectedMovieForScreen = movieRepository.getMovie(id)
            withContext(Dispatchers.Main){
                _selectedMovieScreen.value = newSelectedMovieForScreen
            }
        }
    }

    fun getPopular(){

        if (popularMovieList.value == null){
            viewModelScope.launch(Dispatchers.IO) {
                val popularMovieList: MoviesDTO = movieRepository.getPopular()

                withContext(Dispatchers.Main){
                    _popularMovieList.value = popularMovieList.results
                }
            }
        }

    }
}