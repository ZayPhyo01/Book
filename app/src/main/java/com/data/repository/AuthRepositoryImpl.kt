package com.data.repository

import com.data.datasource.AuthRemoteDataSource

class AuthRepositoryImpl constructor(
    private val authRemoteDataSource: AuthRemoteDataSource
) : AuthRepository {

    override suspend fun login(username: String, password: String): Result<Unit> {
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