package com.data.repository

import com.data.datasource.AuthLocalDatasource
import com.data.datasource.AuthRemoteDataSource
import com.tencent.mmkv.MMKV

class AuthRepository constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authLocalDatasource: AuthLocalDatasource
) {

    suspend fun login(username: String, password: String): Result<Unit> {
        return authRemoteDataSource
            .login(username, password)
            .map { authLocalDatasource.saveAccessToken(it) }
    }
}