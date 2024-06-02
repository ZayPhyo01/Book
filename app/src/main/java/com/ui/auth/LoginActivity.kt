package com.ui.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.ui.BaseActivity
import com.ui.book.HomeActivity
import com.ui.book.R
import com.ui.loader.LoadingManager
import com.ui.viewmodel.LoginUiEvent
import com.ui.viewmodel.LoginUiState
import com.ui.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity() {

    private val viewModel: LoginViewModel by viewModel()
    private lateinit var btnLogin: Button
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var loader: CircularProgressIndicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etUsername = findViewById(R.id.et_username)
        etPassword = findViewById(R.id.et_password)
        btnLogin = findViewById(R.id.btn_login)
        loader = findViewById(R.id.loader)

        etUsername.addTextChangedListener {
            viewModel.username = it.toString()
        }

        etPassword.addTextChangedListener {
            viewModel.password = it.toString()
        }

        btnLogin.setOnClickListener {
            viewModel.login()
        }

        viewModel
            .uiState
            .observe(this) {
                when (it) {

                    is LoginUiState.Loading -> if (it.isLoading) LoadingManager.showLoading() else LoadingManager.hideLoading()

                    is LoginUiState.EnableLoginButton -> btnLogin.isEnabled = it.isEnabled
                }
            }

        viewModel
            .uiEvent
            .observe(this) {
                when (it) {
                    is LoginUiEvent.NewUserEvent -> Toast.makeText(
                        this,
                        "New User",
                        Toast.LENGTH_LONG
                    )
                        .show()

                    is LoginUiEvent.LoginSuccessEvent -> HomeActivity.newInstance(this@LoginActivity)
                        .also {
                            startActivity(it)
                        }

                    is LoginUiEvent.Error -> Toast.makeText(
                        this,
                        it.errorMessage,
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            }
    }

    companion object {
        fun newInstance(ctx: Context): Intent = Intent(
            ctx, LoginActivity::class.java
        ).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
    }
}