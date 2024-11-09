package com.example.imdb_v2.view.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdb_v2.domain.usecases.UserUseCases
import com.example.imdb_v2.util.toUserUI
import com.example.imdb_v2.view.model.UserUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {
    fun login(email : String, password : String){
        viewModelScope.launch(Dispatchers.IO) {
            val user = userUseCases.login(email, password)
            Log.e(">>>", user.toString())
        }
    }

}