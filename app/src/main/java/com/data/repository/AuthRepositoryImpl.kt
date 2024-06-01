package com.data.repository

import com.data.datasource.AuthLocalDataSource
import com.data.datasource.AuthRemoteDataSource

class AuthRepositoryImpl constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authLocalDataSource: AuthLocalDataSource
) : AuthRepository {

    override suspend fun login(username: String, password: String): Result<Unit> {
        return authRemoteDataSource
            .login(
                username = username,
                password = password
            )
            .map {
                //Save to SharedPref
                authLocalDataSource.saveAccessToken(
                    token = it
                )
                //Log.d("ACCESS_TOKEN", authLocalDataSource.isUserLoggedIn().toString())
            }
    }

    override fun isUserLoggedIn() = authLocalDataSource.isUserLoggedIn()
}