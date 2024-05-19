package com.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.data.datasource.ApiException
import com.data.repository.BookRepository
import com.domain.model.BookModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BookViewModel constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    private val _bookListLiveData: MutableLiveData<List<BookModel>> = MutableLiveData()
    private val _errorLiveData: MutableLiveData<String?> = MutableLiveData()
    val errorLiveData: LiveData<String?> = _errorLiveData
    val bookListLiveData: LiveData<List<BookModel>> = _bookListLiveData
    private val _newUserLiveData: MutableLiveData<Unit?> = MutableLiveData()

    init {
        viewModelScope.launch {
            delay(5000)
            fetchBookList()
        }

    }

    private suspend fun fetchBookList() {
        val result = bookRepository.getBookList()
        result.fold(
            onSuccess = {
                _bookListLiveData.value = it
            },
            onFailure = {
                when (it) {
                    is ApiException -> handleApiError(it)
                    else -> _errorLiveData.value = "Unknown error: ${it.message}"
                }
            }
        )
    }

    private fun handleApiError(apiError: ApiException) {
        when (apiError.code) {
            404 -> {
                _newUserLiveData.value = Unit
            }

            else -> {
                _errorLiveData.value = apiError.message
            }
        }
    }

}