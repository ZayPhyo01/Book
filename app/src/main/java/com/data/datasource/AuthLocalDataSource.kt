package com.data.datasource

interface AuthLocalDataSource {
    fun isUserLoggedIn(): Boolean
    fun saveAccessToken(token: String)
    fun getAccessToken(): String?
    fun removeAccessToken()
}