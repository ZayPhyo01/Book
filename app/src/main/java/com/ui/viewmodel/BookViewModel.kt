package com.ui.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.data.repository.BookDomainError
import com.data.repository.BookRepository
import com.domain.model.BookModel
import com.util.SingleLiveEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//Loading -> Loading , Success -> list display
class BookViewModel constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    private val _bookListLiveData: MediatorLiveData<BookUiState> = MediatorLiveData()
    val bookListLiveData: LiveData<BookUiState> = _bookListLiveData

    // one time event
    private val _uiEvent: SingleLiveEvent<BookUiEvent> = SingleLiveEvent()
    val uiEvent: LiveData<BookUiEvent> = _uiEvent

    init {

        _bookListLiveData.addSource(bookRepository.bookListLD) { books ->
            _bookListLiveData.value = BookUiState.Success(books)
        }

        viewModelScope.launch {
            fetchBookList()
        }

    }

    @SuppressLint("CheckResult")
    private suspend fun fetchBookList() {
        //emit loading
        _bookListLiveData.value = BookUiState.Loading
        //delay(5000)
        try {
            bookRepository.getBookList()
           // _bookListLiveData.value = bookRepository.bookListLD.value?.let { BookUiState.Success(books = it) }

        } catch (bookException: BookDomainError) {
            _uiEvent.value = BookUiEvent.Error(bookException.message)
        }
    }

    fun post() {
        viewModelScope.launch {
            delay(2000)
            _uiEvent.value = BookUiEvent.NavigateToDetailScreen
        }

    }
}

sealed class BookUiEvent {
    data object NavigateToDetailScreen : BookUiEvent()

    data class Error(
        val error: String
    ) : BookUiEvent()

}

// Loading or Success -> Or -> seal
sealed class BookUiState {
    data object Loading : BookUiState()

    data class Success(
        val books: List<BookModel>
    ) : BookUiState()

    data object EmptyBook : BookUiState()

}