package com.example.loginscreen.data.repository
import com.example.loginscreen.data.model.LoginResponse
import retrofit2.Response

interface UserRepository {
    suspend fun login(username: String, password: String): Response<LoginResponse>
}
