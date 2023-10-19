package com.example.loginscreen.data.repository

import ApiService
import com.example.loginscreen.data.model.LoginRequest
import com.example.loginscreen.data.model.LoginResponse
import retrofit2.Response

class UserRepositoryImpl(private val apiService: ApiService) : UserRepository {

    override suspend fun login(username: String, password: String): Response<LoginResponse> {
        val loginRequest = LoginRequest(username, password)
        return apiService.login(loginRequest)
    }
}
