package com.example.imdb_v2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdb_v2.navigation.MovieScreenEnum
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BottomNavigationBarViewModel: ViewModel(){

    private val _activeScreen = MutableLiveData(MovieScreenEnum.Home.name)
    val activeScreen : LiveData<String> get() = _activeScreen

    fun changeActiveScreen(screenName : String){
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main){
                _activeScreen.value = screenName
            }
        }
    }
}