package com.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.data.datasource.AuthLocalDatasource

class SplashViewModel(
    private val authLocalDataSource: AuthLocalDatasource
) : ViewModel() {

    private val _isUserLoggedIn: MutableLiveData<Boolean> = MutableLiveData()
    val isUserLoggedIn: LiveData<Boolean> = _isUserLoggedIn

    init {
        checkLoginStatus()
    }

    private fun checkLoginStatus() {
        _isUserLoggedIn.value = authLocalDataSource.isUserLoggedIn()
    }
}