package com.example.loginscreen

import LoginViewModel
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.test.resetMain
import org.junit.After
import com.example.loginscreen.data.model.LoginResponse
import com.example.loginscreen.data.repository.UserRepository
import com.example.loginscreen.ui.viewmodel.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class LoginViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun testLoginSuccess() = runTest {
        val mockResponse = Response.success(LoginResponse("abc123", null))
        Mockito.`when`(userRepository.login("testuser", "testpassword")).thenReturn(mockResponse)

        val sut = LoginViewModel(userRepository)
        sut.login("testuser", "testpassword")
        testDispatcher.scheduler.advanceUntilIdle()
        val result = sut.loginResultLiveData.getOrAwaitValue()
        println(result)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}