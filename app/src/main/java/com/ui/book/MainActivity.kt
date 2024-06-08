package com.ui.book

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.ui.AccountActivity
import com.ui.DetailActivity
import com.ui.adapter.BookAdapter
import com.ui.auth.LoginActivity
import com.ui.base.BaseActivity
import com.ui.book.databinding.ActivityMainBinding
import com.ui.viewmodel.BookUiEvent
import com.ui.viewmodel.BookUiState
import com.ui.viewmodel.BookViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    val bookViewModel: BookViewModel by viewModel()
    var bookRecyclerView: RecyclerView? = null
    val adapter: BookAdapter = BookAdapter()
    var tvResponse: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val activityMainBinding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        bookRecyclerView = activityMainBinding.rvBookList



        bookRecyclerView?.adapter = adapter
        tvResponse = activityMainBinding.tvResponse

        bookViewModel.bookListLiveData.observe(this) {
            when (it) {
                BookUiState.Loading -> {
                    activityMainBinding.pgLoading.visibility = View.VISIBLE
                }

                is BookUiState.Success -> {
                    activityMainBinding.pgLoading.visibility = View.GONE
                    tvResponse?.text = "Success"
                    Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
                    adapter.updateList(it.books)
                }

                BookUiState.EmptyBook -> {
                    //showEmptyView()
                }

                BookUiState.NavigateToDetailScreen -> {
                    val intent = Intent(this, DetailActivity::class.java)
                    startActivity(intent)
                }
            }

        }

        bookViewModel.bookUiEvent
            .observe(this) {
                when (it) {
                    is BookUiEvent.Error -> Toast.makeText(
                        this@MainActivity,
                        it.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }


        val btn = activityMainBinding.btnClick
        btn.setOnClickListener {
            //GotoAccountActivity
            AccountActivity.newIntent(this).also {
                startActivity(it)
            }
        }

    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    //
    override fun onPause() {
        super.onPause()

    }

    override fun onStop() {
        super.onStop()
        bookRecyclerView = null
        tvResponse = null

    }

    override fun onDestroy() {
        super.onDestroy()

    }

    companion object {
        fun newInstance(ctx: Context): Intent {
            return Intent(
                ctx, MainActivity::class.java
            ).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        }
    }
}

//BookList -> 10 sec -> btn -> Detail
//5sec