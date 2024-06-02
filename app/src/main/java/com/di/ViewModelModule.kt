package com.di

import com.ui.SplashViewModel
import com.ui.viewmodel.BookViewModel
import com.ui.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val bookViewModelModule = module {
    viewModel {
        BookViewModel(get())
    }
}

val splashViewModelModule = module {
    viewModel {
        SplashViewModel(
            get()
        )
    }
}

val loginViewModelModule = module {
    viewModel {
        LoginViewModel(get())
    }
}