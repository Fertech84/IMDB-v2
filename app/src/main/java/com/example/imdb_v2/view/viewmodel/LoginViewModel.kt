package com.example.imdb_v2.view.viewmodel

import android.util.Log
import androidx.datastore.dataStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdb_v2.data.local.preferences.DatastoreLocalManager
import com.example.imdb_v2.domain.usecases.UserUseCases
import com.example.imdb_v2.util.toUserUI
import com.example.imdb_v2.view.model.UserUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
    private val userDataStore: DatastoreLocalManager
) : ViewModel() {


    private val _loginStatus = MutableLiveData<LoginStatus>()
    val loginStatus: LiveData<LoginStatus> get() = _loginStatus

    init {
        initializeLoginSettings()
        Log.e(">>>", loginStatus.value.toString())
    }

    fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = userUseCases.login(email, password)

            if (user != null) {
                userDataStore.saveUserId(user.uid.toInt())
            }
            withContext(Dispatchers.Main) {
                _loginStatus.value = when (user) {
                    null -> LoginStatus.loginFailed
                    else -> {
                        LoginStatus.loginSuccess

                    }
                }
            }
        }
    }

    private fun initializeLoginSettings() {
        viewModelScope.launch(Dispatchers.IO) {
            val userIdFlow = userDataStore.getUserID
            val userId = userIdFlow.first()

            withContext(Dispatchers.Main) {
                if (userId != -1) {
                    _loginStatus.value = LoginStatus.loginSuccess
                }else{
                    _loginStatus.value = LoginStatus.loginPaused
                }
            }
        }
    }

    fun reloadLoginState() {
        _loginStatus.value = LoginStatus.loginPaused
    }

}

sealed class LoginStatus() {
    object loginPaused : LoginStatus()
    object loginSuccess : LoginStatus()
    object loginFailed : LoginStatus()
}