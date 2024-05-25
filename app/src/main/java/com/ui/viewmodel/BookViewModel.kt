package com.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.data.repository.BookRepository
import com.domain.model.BookModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//Loading -> Loading , Success -> list display
class BookViewModel constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    private val _bookListLiveData: MutableLiveData<BookUiState> = MutableLiveData()
    val bookListLiveData: LiveData<BookUiState> = _bookListLiveData

    init {
        viewModelScope.launch {
            fetchBookList()
        }

    }

    private suspend fun fetchBookList() {
        //emit loading
        _bookListLiveData.value = BookUiState.Loading
        delay(5000)
        val bookModels: List<BookModel> = bookRepository.getBookList()
        _bookListLiveData.value = BookUiState.Success(bookModels)
    }

    fun post() {
        viewModelScope.launch {
            delay(2000)
            _bookListLiveData.value = BookUiState.NavigateToDetailScreen
        }

    }
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