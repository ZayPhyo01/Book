package com.data.datasource

import androidx.lifecycle.LiveData
import com.domain.model.UserModel

interface AuthLocalDataSource {
    fun isUserLoggedIn(): Boolean
    fun saveAccessToken(token: String)
    fun getAccessToken(): String?
    fun removeAccessToken()
    suspend fun saveUserInfo(user: UserModel)
    fun getUserInfo(): LiveData<UserModel>
    suspend fun getUserInfoOnce(): UserModel?
}