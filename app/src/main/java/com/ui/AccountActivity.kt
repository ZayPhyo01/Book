package com.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.domain.model.UserModel
import com.ui.book.R
import com.ui.book.databinding.ActivityAccountBinding
import com.ui.book.databinding.ActivityMainBinding
import com.ui.viewmodel.AccountUiState
import com.ui.viewmodel.AccountViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AccountActivity : AppCompatActivity() {
    private var binding: ActivityAccountBinding? = null

    private val viewModel: AccountViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel
            .uiState
            .observe(this) {
                when (it) {
                    is AccountUiState.SuccessGetUser -> {
                        bindData(
                            user = it.user
                        )
                    }
                }
            }

    }

    private fun bindData(user: UserModel) {
        binding?.tvUsername?.text = user.userName
        binding?.tvEmail?.text = user.email
        binding?.tvPhonenumber?.text = user.phoneNumber

    }

    companion object {
        fun newIntent(ctx: Context) = Intent(
            ctx, AccountActivity::class.java
        )
    }
}