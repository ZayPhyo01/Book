package com.ui.book

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.ui.DetailActivity
import com.ui.adapter.BookAdapter
import com.ui.viewmodel.BookViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val bookViewModel: BookViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val bookRecyclerView = findViewById<RecyclerView>(R.id.rvBookList)
        val adapter: BookAdapter = BookAdapter()
        bookRecyclerView.adapter = adapter

        bookViewModel.bookListLiveData.observe(this) {
            adapter.updateList(it)
        }

        val btn = findViewById<Button>(R.id.btnClick)
        btn.setOnClickListener {
            val intent = Intent(this , DetailActivity::class.java)
            startActivity(intent)
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
        Toast.makeText(this , "onPause" , Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this , "onStop" , Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this , "onDestroy" , Toast.LENGTH_SHORT).show()
    }
}

//BookList -> 10 sec -> btn -> Detail
//5sec