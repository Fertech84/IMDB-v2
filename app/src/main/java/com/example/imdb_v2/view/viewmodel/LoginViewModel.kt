package com.example.imdb_v2.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdb_v2.domain.usecases.UserUseCases
import com.example.imdb_v2.util.toUserUI
import com.example.imdb_v2.view.model.UserUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {


    private val _loginStatus = MutableLiveData<LoginStatus>()
    val loginStatus : LiveData<LoginStatus> get() = _loginStatus

    init {
        _loginStatus.value = LoginStatus.loginPaused //must recovery from preferences
    }

    fun login(email : String, password : String){
        viewModelScope.launch(Dispatchers.IO) {
            val user = userUseCases.login(email, password)
            withContext(Dispatchers.Main){
                _loginStatus.value = when (user){
                    null -> LoginStatus.loginFailed
                    else -> LoginStatus.loginSuccess
                }
            }
        }
    }

    fun reloadLoginState(){
        _loginStatus.value = LoginStatus.loginPaused
    }

}

sealed class LoginStatus{
    object loginPaused : LoginStatus()
    object loginSuccess : LoginStatus()
    object loginFailed : LoginStatus()
}