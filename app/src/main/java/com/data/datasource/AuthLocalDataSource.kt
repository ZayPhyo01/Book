package com.data.datasource

interface AuthLocalDataSource {
    fun isUserLoggedIn(): Boolean
    fun saveAccessToken(token: String)
}