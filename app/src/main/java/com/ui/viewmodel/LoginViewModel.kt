package com.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.data.exceptions.ApiException
import com.data.repository.AuthRepository
import kotlinx.coroutines.launch

sealed class LoginUiState {
    data object Loading : LoginUiState()
    data class Error(val error: String) : LoginUiState()
    data object LoginSuccess : LoginUiState()
    data object NewUser : LoginUiState()
}

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState: MutableLiveData<LoginUiState> = MutableLiveData()
    val uiState: LiveData<LoginUiState> = _uiState

    fun login(userName: String, password: String) {
        //Show loading -> State
        //repo.login
        //error handling
        // error -> State
        _uiState.value = LoginUiState.Loading
        viewModelScope.launch {
            authRepository
                .login(
                    username = userName,
                    password = password
                )
                .fold(
                    onSuccess = {
                        _uiState.value = LoginUiState.LoginSuccess
                    },

                    onFailure = { error ->
                        //error -> Throwable
                        // ApiExcpetion or Exception
                        when (error) {
                            is ApiException -> handleApiException(error)

                            else -> {
                                //Exception
                                _uiState.value =
                                    LoginUiState.Error(error.message ?: "Something went wrong")
                            }
                        }

                    }
                )
        }

    }

    private fun handleApiException(apiException: ApiException) {
        when (apiException.code) {
            404 -> {
                _uiState.value = LoginUiState.NewUser
            }

            else -> {
                _uiState.value = LoginUiState
                    .Error(apiException.message ?: "Something went wrong")
            }
        }
    }
}