package com.data.repository

import com.data.datasource.AuthRemoteDataSource

class AuthRepository constructor(
    private val authRemoteDataSource: AuthRemoteDataSource
) {

    suspend fun login(username: String, password: String): Result<Unit> {
        return authRemoteDataSource
            .login(
                username = username,
                password = password
            )
            .map {
                //Save to SharedPref
                //localdatascource
            }
    }
}