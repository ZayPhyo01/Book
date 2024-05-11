package com.di

import com.ui.viewmodel.BookViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val bookViewModelModule = module {
    viewModel {
        BookViewModel(get())
    }
}