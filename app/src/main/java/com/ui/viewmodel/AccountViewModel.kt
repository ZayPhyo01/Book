package com.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.data.repository.AuthRepository
import com.data.repository.LogRepository
import com.domain.model.UserModel
import com.ui.base.BaseViewModel
import kotlinx.coroutines.launch

sealed class AccountUiState {
    data class SuccessGetUser(
        val user: UserModel
    ) : AccountUiState()
}

class AccountViewModel(
    private val authRepository: AuthRepository,
    private val logRepository: LogRepository
) : BaseViewModel(logRepository) {

    private val _uiState: MediatorLiveData<AccountUiState> = MediatorLiveData()
    val uiState: LiveData<AccountUiState> = _uiState

    init {
        viewModelScope.launch {
            setScreenName("ACCOUNT")
        }
        _uiState.addSource(authRepository.getUser()) {
            _uiState.value = AccountUiState.SuccessGetUser(it)
        }

    }
}