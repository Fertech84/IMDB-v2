package com.example.imdb_v2.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdb_v2.model.MovieDTO
import com.example.imdb_v2.model.MoviesDTO
import com.example.imdb_v2.repository.MovieRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieViewmodel @Inject constructor(
    private val movieRepository : MovieRepositoryImpl
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

    private val _searchMovieList = MutableLiveData<List<MovieDTO>>()
    val searchMovieList : LiveData<List<MovieDTO>> get() = _searchMovieList


    init {
        getTopRated()
        getPopular()
        _searchMovieList.value = emptyList()
    }


    fun searchMovie(movieName : String){
        viewModelScope.launch(Dispatchers.IO) {
            val searchResults  = movieRepository.searchMovie(movieName)
            withContext(Dispatchers.Main){
                _searchMovieList.value = searchResults.results
            }
        }
    }
    private fun getTopRated() {
        if (topRatedMovieList.value == null) {
            viewModelScope.launch(Dispatchers.IO) {
                val topRatedMovieList: MoviesDTO = movieRepository.getTopRated()

                withContext(Dispatchers.Main) {
                    _topRatedMovieList.value = topRatedMovieList.results
                    _previewMovieItem.value = topRatedMovieList.results[0]
                    _selectedMovieScreen.value = topRatedMovieList.results[0]
                }
            }
        }
    }

    fun changeSelectedMovieScreen(id : String){
        viewModelScope.launch(Dispatchers.IO){
            val newSelectedMovieForScreen = movieRepository.getMovie(id)
            Log.e("New Selected Movie >>>>>", "$newSelectedMovieForScreen")
            withContext(Dispatchers.Main){
                _selectedMovieScreen.value = newSelectedMovieForScreen
            }
        }
    }

    private fun getPopular(){

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