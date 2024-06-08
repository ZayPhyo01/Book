package com.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.data.repository.AuthRepository
import com.domain.model.UserModel

sealed class AccountUiState {
    data class SuccessGetUser(
        val user: UserModel
    ) : AccountUiState()
}

class AccountViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState: MediatorLiveData<AccountUiState> = MediatorLiveData()
    val uiState: LiveData<AccountUiState> = _uiState

    init {
        _uiState.addSource(authRepository.getUser()) {
            _uiState.value = AccountUiState.SuccessGetUser(it)
        }

    }
}