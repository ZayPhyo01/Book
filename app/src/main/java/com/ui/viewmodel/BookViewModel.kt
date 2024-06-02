package com.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.data.exceptions.ApiException
import com.data.repository.AuthRepository
import com.data.repository.BookRepository
import com.data.repository.BookRepositoryImpl
import com.domain.model.BookModel
import com.util.SingleLiveEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//Loading -> Loading , Success -> list display
class BookViewModel constructor(
    private val bookRepository: BookRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _bookListLiveData: MutableLiveData<BookUiState> = MutableLiveData()
    val bookListLiveData: LiveData<BookUiState> = _bookListLiveData

    private val _bookUiEvent: SingleLiveEvent<BookUiEvent> = SingleLiveEvent()
    val bookUiEvent: LiveData<BookUiEvent> = _bookUiEvent

    init {
        viewModelScope.launch {
            fetchBookList()
        }

    }

    private suspend fun fetchBookList() {
        //emit loading
        _bookListLiveData.value = BookUiState.Loading
        delay(5000)
        bookRepository
            .getBookList()
            .fold(
                onSuccess = {
                    _bookListLiveData.value = BookUiState.Success(it)
                },
                onFailure = { error ->
                    when (error) {
                        is ApiException -> handleApiException(error)
                        else -> _bookUiEvent.value =
                            BookUiEvent.Error(error.message ?: "Something went wrong")
                    }
                }
            )
//        val bookList = bookRepository.getBookList().getOrNull()
//        if (bookList == null) {
//            // fail -> showErrorMessage
//        } else {
//            _bookListLiveData.value = BookUiState.Success(bookList)
//        }
    }

    private fun handleApiException(apiException: ApiException) {
        _bookUiEvent.value = BookUiEvent
            .Error(apiException.message ?: "Something went wrong")
    }

    fun post() {
        viewModelScope.launch {
            delay(2000)
            _bookListLiveData.value = BookUiState.NavigateToDetailScreen
        }

    }
}

sealed class BookUiEvent {
    data class Error(val message: String) : BookUiEvent()
}

// Loading or Success -> Or -> seal
sealed class BookUiState {
    object Loading : BookUiState()

    data class Success(
        val books: List<BookModel>
    ) : BookUiState()

    object EmptyBook : BookUiState()

    object NavigateToDetailScreen : BookUiState()
}