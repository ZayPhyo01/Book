package com.ui.auth

import android.os.Bundle
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.appcompat.app.AppCompatActivity
import com.ui.book.databinding.ActivityLoginBinding
import com.ui.viewmodel.LoginViewModel


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
    }
}