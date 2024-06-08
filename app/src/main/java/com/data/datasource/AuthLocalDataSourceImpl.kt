package com.data.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.data.db.dao.UserDao
import com.data.mapper.toEntity
import com.data.mapper.toModel
import com.domain.model.UserModel

class AuthLocalDataSourceImpl(
    private val userDao: UserDao
) : AuthLocalDataSource {

    private val tokenService: TokenService = TokenService
    override fun isUserLoggedIn() = !tokenService.token.isNullOrBlank()
    override fun saveAccessToken(token: String) {
        tokenService.token = token
    }

    override fun getAccessToken(): String? = tokenService.token

    override fun removeAccessToken() {
        tokenService.token = null
    }

    override suspend fun saveUserInfo(user: UserModel) {
        userDao.saveUser(
            user = user.toEntity()
        )
    }

    override fun getUserInfo(): LiveData<UserModel> = userDao.getUser().map { it.toModel() }

    override suspend fun getUserInfoOnce(): UserModel? = userDao.getUserOnce()?.toModel()
}