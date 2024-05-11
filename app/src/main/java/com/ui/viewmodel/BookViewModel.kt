package com.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.data.repository.BookRepository
import com.domain.model.BookModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BookViewModel constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    private val _bookListLiveData: MutableLiveData<List<BookModel>> = MutableLiveData()
    val bookListLiveData: LiveData<List<BookModel>> = _bookListLiveData

    init {
        viewModelScope.launch {
            delay(5000)
            fetchBookList()
        }

    }

    private suspend fun fetchBookList() {
        val bookModels: List<BookModel> = bookRepository.getBookList()
        _bookListLiveData.value = bookModels
    }

}