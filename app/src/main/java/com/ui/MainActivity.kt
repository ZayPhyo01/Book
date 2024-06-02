package com.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ui.auth.LoginActivity
import com.ui.book.HomeActivity
import com.ui.book.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val splashViewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        splashViewModel
            .isUserLoggedIn
            .observe(this) {
                if (it) {
                    //Go to home
                    HomeActivity.newInstance(this@MainActivity).also {
                        startActivity(it)
                    }
                } else {
                    //Go to Login
                    LoginActivity.newInstance(
                        this@MainActivity
                    ).also {
                        startActivity(it)
                    }
                }
            }
    }
}