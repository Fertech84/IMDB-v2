package com.example.imdb_v2.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdb_v2.data.network.MovieService
import com.example.imdb_v2.domain.usecases.MovieUseCases
import com.example.imdb_v2.util.ToMovieUI
import com.example.imdb_v2.util.toUI
import com.example.imdb_v2.view.model.MovieUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieViewmodel @Inject constructor(
    private val movieUseCases: MovieUseCases
) : ViewModel() {

    //Top rated state
    private val _topRatedMovieList = MutableLiveData<List<MovieUI>>()
    val topRatedMovieList: LiveData<List<MovieUI>> get() = _topRatedMovieList

    //Popular state
    private val _popularMovieList = MutableLiveData<List<MovieUI>>()
    val popularMovieList: LiveData<List<MovieUI>> get() = _popularMovieList

    private val _previewMovieItem = MutableLiveData<MovieUI>()
    val previewMovieItem : LiveData<MovieUI> get() = _previewMovieItem

    private val _selectedMovieScreen = MutableLiveData<MovieUI>()
    val selectedMovieScreen : LiveData<MovieUI> get() = _selectedMovieScreen

    private val _searchMovieList = MutableLiveData<List<MovieUI>>()
    val searchMovieList : LiveData<List<MovieUI>> get() = _searchMovieList


    init {
        getTopRated()
        getPopular()
        _searchMovieList.value = emptyList()
    }


    fun searchMovie(movieName : String){
        viewModelScope.launch(Dispatchers.IO) {
            val results  = movieUseCases.searchMovie(movieName).map {
               it.ToMovieUI()
            }




            withContext(Dispatchers.Main){
                _searchMovieList.value = results
            }
        }
    }
    private fun getTopRated() {
        if (topRatedMovieList.value == null) {
            viewModelScope.launch(Dispatchers.IO) {
                val topRatedMovieList = movieUseCases.getTopRated().map {
                    it.ToMovieUI()
                }

                withContext(Dispatchers.Main) {
                    _topRatedMovieList.value = topRatedMovieList
                    _previewMovieItem.value = topRatedMovieList[0]
                    _selectedMovieScreen.value = topRatedMovieList[0]
                }
            }
        }
    }

    fun changeSelectedMovieScreen(id : String){
        viewModelScope.launch(Dispatchers.IO){
            val newSelectedMovieForScreen = movieUseCases.getMovie(id.toInt()).let {
                it!!.ToMovieUI()
            }
            withContext(Dispatchers.Main){
                _selectedMovieScreen.value = newSelectedMovieForScreen
            }
        }
    }

    private fun getPopular(){

        if (popularMovieList.value == null){
            viewModelScope.launch(Dispatchers.IO) {
                val popularMovieList = movieUseCases.getPopular().map {
                    it.ToMovieUI()
                }

                withContext(Dispatchers.Main){
                    _popularMovieList.value = popularMovieList

                }
            }
        }

    }
}

