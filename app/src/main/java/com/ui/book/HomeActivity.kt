package com.ui.book

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.data.service.GlobalEventManager
import com.data.service.GlobalEvents
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ui.BaseActivity
import com.ui.auth.LoginActivity
import com.ui.book.fragments.BookListFragment

class HomeActivity : BaseActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_book_list -> {
                    replaceFragment(BookListFragment.newInstance())
                }

                R.id.nav_fav_book_list -> {
                    replaceFragment(BookListFragment.newInstance())
                }

                else -> {
                    replaceFragment(BookListFragment.newInstance())
                }
            }
            true
        }
        replaceFragment(BookListFragment.newInstance())


    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment, fragment.javaClass.name)
            .commit()
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
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance(ctx: Context): Intent {
            return Intent(
                ctx, HomeActivity::class.java
            ).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        }
    }
}

//BookList -> 10 sec -> btn -> Detail
//5sec