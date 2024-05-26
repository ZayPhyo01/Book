package com.data.service

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

// http://54.179.102.152/api/user/books_simple
class KtorUtils {
    fun createKtor(
        context: Context
    ): HttpClient {
        return HttpClient(OkHttp) {
            engine {
                addInterceptor(
                    ChuckerInterceptor(
                        context = context
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
        }
    }
}