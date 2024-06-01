package com.data.repository

interface AuthRepository {

    suspend fun login(username: String, password: String): Result<Unit>
}