package com.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.appcompat.app.AppCompatActivity
import com.ui.book.MainActivity
import com.ui.book.databinding.ActivityLoginBinding
import com.ui.viewmodel.LoginUiState
import com.ui.viewmodel.LoginViewModel
import com.ui.viewmodel.LoginViewModelEvent


class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModel()
    private var binding: ActivityLoginBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btnLogin?.setOnClickListener {
            val email = binding?.edtEmail?.text.toString()
            val password = binding?.edtPassword?.text.toString()
            viewModel.login(
                userName = email,
                password = password
            )
        }



        observerUiState()
        observeViewModelEvent()


    }

    private fun observeViewModelEvent() {
        viewModel.uiEvent.observe(this) {
            when (it) {
                is LoginViewModelEvent.Error -> {
                    Toast.makeText(this, it.error, Toast.LENGTH_SHORT)
                        .show()
                }


                LoginViewModelEvent.LoginSuccess -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }

                LoginViewModelEvent.NewUser -> {
                    Toast.makeText(this, "new user", Toast.LENGTH_SHORT)
                        .show()
                }

            }
        }
    }

    private fun observerUiState() {
        viewModel.uiState.observe(this) {
            when (it) {
                LoginUiState.Loading -> {}

                LoginUiState.Idle -> {

                }
            }
        }
    }

}