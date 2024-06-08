package com.data.repository

import androidx.lifecycle.LiveData
import com.data.datasource.AuthLocalDataSource
import com.data.datasource.AuthRemoteDataSource
import com.domain.model.UserModel

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
                    token = it.accessToken
                )
                //SaveUserInfo
                authLocalDataSource.saveUserInfo(
                    user = UserModel(
                        userName = it.userName ?: "",
                        email = it.email ?: "",
                        phoneNumber = it.phoneNumber ?: ""
                    )
                )

            }
    }

    override fun isUserLoggedIn() = authLocalDataSource.isUserLoggedIn()
    override fun removeAccessToken() {
        authLocalDataSource.removeAccessToken()
    }

    override fun getUser(): LiveData<UserModel> {
        return authLocalDataSource.getUserInfo()
    }

    override suspend fun getUserOnce(): UserModel? {
        return authLocalDataSource.getUserInfoOnce()
    }
}