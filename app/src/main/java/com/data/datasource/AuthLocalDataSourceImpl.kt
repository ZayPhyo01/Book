package com.data.datasource

class AuthLocalDataSourceImpl : AuthLocalDataSource {

    private val tokenService: TokenService = TokenService
    override fun isUserLoggedIn() = !tokenService.token.isNullOrBlank()
    override fun saveAccessToken(token: String) {
        tokenService.token = token
    }

}