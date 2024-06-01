package com.data.datasource

interface AuthRemoteDataSource {
    suspend fun login(username: String, password: String): Result<String>
}