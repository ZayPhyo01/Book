package com.data.datasource

import com.data.model.LoginResponse

interface AuthRemoteDataSource {
    suspend fun login(username: String, password: String): Result<LoginResponse.User>
}