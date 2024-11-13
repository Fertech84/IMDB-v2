package com.example.imdb_v2.view.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdb_v2.domain.usecases.UserUseCases
import com.example.imdb_v2.util.toUser
import com.example.imdb_v2.view.model.UserUI
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val userUseCases: UserUseCases
)  : ViewModel() {

    private val _creationStatus = MutableLiveData<SignupStatus>()
    val creationStatus : LiveData<SignupStatus> get() = _creationStatus

    init {
        _creationStatus.value = SignupStatus.paused
    }

    fun createNewUser(userUI : UserUI) {

        viewModelScope.launch(Dispatchers.IO) {
            val resultOfCreation = userUseCases.signup(userUI.toUser())
            Log.e(">>>>>>", "$resultOfCreation")
            withContext(Dispatchers.Main){
                _creationStatus.value = when (resultOfCreation){
                    true -> SignupStatus.success
                    false -> SignupStatus.failed
                }
            }
        }
    }

    fun closeSignup(){
        _creationStatus.value = SignupStatus.paused
    }
}


sealed class SignupStatus{
    object paused : SignupStatus()
    object success : SignupStatus()
    object failed : SignupStatus()
}