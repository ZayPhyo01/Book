package com.di

import com.ui.viewmodel.AccountViewModel
import com.ui.viewmodel.BookViewModel
import com.ui.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val bookViewModelModule = module {
    viewModel {
        BookViewModel(
            bookRepository = get(),
            authRepository = get(),
            logRepository = get()
        )
    }
    viewModel {
        LoginViewModel(
            get(),
            get()
        )
    }
    viewModel {
        AccountViewModel(
            authRepository = get(),
            logRepository = get()
        )
    }
}