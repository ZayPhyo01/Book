package com.ui.auth

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ui.viewmodel.LoginViewModel


class LoginActivity : AppCompatActivity() {

    private val viewModel : LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        btn.onClinckListener {

                viewModel.login("mgmg" , "abc")


        }
    }
}