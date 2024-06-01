package com.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.data.exceptions.ApiException
import com.data.repository.AuthRepository
import com.data.repository.AuthRepositoryImpl
import com.util.SingleLiveEvent
import kotlinx.coroutines.launch

sealed class LoginUiState {

    object Idle : LoginUiState()
    data object Loading : LoginUiState()


}

sealed class LoginViewModelEvent {
    data object LoginSuccess : LoginViewModelEvent()

    data object NewUser : LoginViewModelEvent()

    data class Error(val error: String) : LoginViewModelEvent()

    data object UserAlreadyLoggedIn : LoginViewModelEvent()
}

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    // persist ui state
    private val _uiState: MutableLiveData<LoginUiState> = MutableLiveData()
    val uiState: LiveData<LoginUiState> = _uiState

    // one time event
    private val _uiEvent: SingleLiveEvent<LoginViewModelEvent> = SingleLiveEvent()
    val uiEvent: LiveData<LoginViewModelEvent> = _uiEvent

    init {
        if (authRepository.isUserLoggedIn()) {
            _uiEvent.value = LoginViewModelEvent.UserAlreadyLoggedIn
        }
    }

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
                        _uiEvent.value = LoginViewModelEvent.LoginSuccess
                    },

                    onFailure = { error ->
                        //error -> Throwable
                        // ApiExcpetion or Exception
                        when (error) {
                            is ApiException -> handleApiException(error)

                            else -> {
                                //Exception
                                _uiEvent.value =
                                    LoginViewModelEvent.Error(
                                        error.message ?: "Something went wrong"
                                    )
                            }
                        }

                    }
                )



        }

    }

    fun onMessageShown() {
        _uiState.value = LoginUiState.Idle
    }

    private fun handleApiException(apiException: ApiException) {
        when (apiException.code) {
            404 -> {
                _uiEvent.value = LoginViewModelEvent.NewUser
            }

            else -> {
                _uiEvent.value = LoginViewModelEvent
                    .Error(apiException.message ?: "Something went wrong")
            }
        }
    }
}