package com.data.datasource

import com.data.service.TokenService
import com.tencent.mmkv.MMKV

class AuthLocalDatasource {
    private val tokenService: TokenService = TokenService
    fun isUserLoggedIn() = !tokenService.token.isNullOrEmpty()

    fun saveAccessToken(token: String) {
        tokenService.token = token
    }
}