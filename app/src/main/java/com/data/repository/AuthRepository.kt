package com.data.repository

import androidx.lifecycle.LiveData
import com.domain.model.UserModel

interface AuthRepository {

    suspend fun login(username: String, password: String): Result<Unit>

    fun isUserLoggedIn(): Boolean

    fun removeAccessToken()

    fun getUser(): LiveData<UserModel>

    suspend fun getUserOnce(): UserModel?
}