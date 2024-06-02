package com.data.service

import android.content.Context
import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.HttpSendPipeline
import io.ktor.client.request.bearerAuth
import io.ktor.http.HttpStatusCode
import io.ktor.http.encodedPath
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import java.util.concurrent.atomic.AtomicBoolean

// http://54.179.102.152/api/user/books_simple
class KtorUtils {
    fun createKtor(appContext: Context): HttpClient {
        return HttpClient(Android) {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
            install(Logging) {
                level = LogLevel.ALL
            }
            install(ResponseObserver) {
                onResponse { response ->
                    if (response.status == HttpStatusCode.Unauthorized) {
                        TokenService.token = null
                        GlobalEventManager
                            .sendUnauthorizedEvent()
                    }
                }
            }
        }.apply {
            sendPipeline
                .intercept(
                    HttpSendPipeline.State
                ) {
                    when (context.url.encodedPath) {
                        "/api/auth/login", "auth/register" -> {}
                        else -> {
                            TokenService.token?.let {
                                context.bearerAuth(
                                    token = it
                                )
                            }

                        }
                    }
                }
        }
    }
}

sealed class GlobalEvents {
    data object Unauthorized : GlobalEvents()
}

object GlobalEventManager {

    private val _globalEvent = SingleLiveEvent<GlobalEvents>()
    val globalEvent: LiveData<GlobalEvents> get() = _globalEvent

    fun sendUnauthorizedEvent() {
        _globalEvent.postValue(GlobalEvents.Unauthorized)
    }
}

class SingleLiveEvent<T> : MutableLiveData<T>() {
    private val mPending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasActiveObservers()) {
            Log.w("TAG", "Multiple observers registered but only one will be notified of changes.")
        }
        // Observe the internal MutableLiveData
        super.observe(owner) { t ->
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        }
    }

    @MainThread
    override fun setValue(t: T?) {
        mPending.set(true)
        super.setValue(t)
    }
}