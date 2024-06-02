package com.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.data.utils.GlobalEventManager
import com.data.utils.GlobalEvents
import com.ui.auth.LoginActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Listen Global Events
        GlobalEventManager.globalEvents.observe(this) { event ->
            when (event) {
                is GlobalEvents.Unauthorized -> {
                    LoginActivity.newInstance(
                        this@BaseActivity
                    ).also { intent ->
                        startActivity(intent)
                    }
                }
            }
        }
    }
}