package com.ui.book

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.ui.DetailActivity
import com.ui.adapter.BookAdapter
import com.ui.book.databinding.ActivityMainBinding
import com.ui.viewmodel.BookUiState
import com.ui.viewmodel.BookViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

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
            when(it){
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




        val btn = activityMainBinding.btnClick
        btn.setOnClickListener {
            bookViewModel.post()
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
}

//BookList -> 10 sec -> btn -> Detail
//5sec