package com.data.service

import android.content.Context
import android.util.Log
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.data.datasource.AuthLocalDataSource
import com.data.utils.GlobalEventManager
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.HttpSendPipeline
import io.ktor.client.request.bearerAuth
import io.ktor.http.HttpStatusCode
import io.ktor.http.encodedPath
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

// http://54.179.102.152/api/user/books_simple
class KtorUtils {
    fun createKtor(
        appContext: Context,
        authLocalDataSource: AuthLocalDataSource
    ): HttpClient {
        return HttpClient(OkHttp) {
            engine {
                addInterceptor(
                    ChuckerInterceptor(
                        context = appContext
                    )
                )
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                    }
                )

            }
            install(ResponseObserver) {
                onResponse { response ->
                    if (response.status == HttpStatusCode.Unauthorized) {
                        authLocalDataSource.removeAccessToken()
                        //Global Event
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
                    Log.d("URL", context.url.encodedPath)
                    when (context.url.encodedPath) {
                        "/api/auth/login",
                        "/api/auth/register_user" -> {
                        }

                        else -> {
                            //Add Token
                            //TokenService or LocalDataSource -> getAccessToken()
                            authLocalDataSource.getAccessToken()?.let {
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