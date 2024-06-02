package com.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.data.service.GlobalEventManager
import com.data.service.GlobalEvents
import com.ui.auth.LoginActivity
import com.ui.loader.LoadingDialog
import com.ui.loader.LoadingManager

abstract class BaseActivity : AppCompatActivity() {

    private var loadingDialog: LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        LoadingManager.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                showLoadingDialog()
            } else {
                hideLoadingDialog()
            }
        }

        GlobalEventManager.globalEvent.observe(this) { event ->
            when (event) {
                is GlobalEvents.Unauthorized -> {
                    LoginActivity.newInstance(
                        this
                    ).also {
                        startActivity(it)
                    }
                }
            }

        }

    }

    private fun showLoadingDialog() {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog.newInstance()
            loadingDialog?.show(supportFragmentManager, LoadingDialog.TAG)
        }
    }

    private fun hideLoadingDialog() {
        loadingDialog?.dismiss()
        loadingDialog = null
    }
}