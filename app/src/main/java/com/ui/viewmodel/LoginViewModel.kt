package com.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.data.SingleLiveData
import com.data.exceptions.ApiException
import com.data.repository.AuthRepository
import com.data.service.GlobalEvents
import com.data.service.SingleLiveEvent
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

sealed class LoginUiState {
    data class Loading(val isLoading: Boolean) : LoginUiState()
    data class EnableLoginButton(val isEnabled: Boolean) : LoginUiState()
}

sealed class LoginUiEvent {
    data class Error(val errorMessage: String) : LoginUiEvent()
    data object NewUserEvent : LoginUiEvent()
    data object LoginSuccessEvent : LoginUiEvent()
}

open class BaseViewModel<S, E> : ViewModel() {
    protected val _uiState = SingleLiveEvent<S>()
    val uiState: LiveData<S> get() = _uiState

    protected val _uiEvent = SingleLiveEvent<E>()
    val uiEvent: LiveData<E> = _uiEvent
}

class LoginViewModel(
    private val authRepository: AuthRepository
) : BaseViewModel<LoginUiState, LoginUiEvent>() {

    var username: String? by Delegates.observable(null) { _, _, _ ->
        validate()
    }
    var password: String? by Delegates.observable(null) { _, _, _ ->
        validate()
    }

    fun login() {
        _uiState.value = LoginUiState.Loading(true)
        viewModelScope.launch {
            authRepository
                .login(username!!, password!!)
                .fold(
                    onSuccess = {
                        _uiState.value = LoginUiState.Loading(false)
                        _uiEvent.value = LoginUiEvent.LoginSuccessEvent
                    },
                    onFailure = { error ->
                        _uiState.value = LoginUiState.Loading(false)
                        when (error) {
                            is ApiException -> handleApiException(error)
                            else -> {
                                _uiEvent.value =
                                    LoginUiEvent.Error(error.message ?: "Something went wrong")
                            }
                        }
                    }
                )
        }
    }

    private fun handleApiException(apiException: ApiException) {
        when (apiException.code) {
            404 -> {
                _uiEvent.value = LoginUiEvent.NewUserEvent
            }

            else -> {
                _uiEvent.value =
                    LoginUiEvent.Error(apiException.message ?: "Something went wrong")
            }
        }
    }

    private fun validate() {
        _uiState.value =
            LoginUiState.EnableLoginButton(!username.isNullOrBlank() && !password.isNullOrBlank())
    }
}