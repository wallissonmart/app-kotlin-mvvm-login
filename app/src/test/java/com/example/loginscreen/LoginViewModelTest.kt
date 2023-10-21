package com.example.loginscreen

import LoginViewModel
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.loginscreen.common.LoginResult
import kotlinx.coroutines.test.resetMain
import org.junit.After
import com.example.loginscreen.data.model.LoginResponse
import com.example.loginscreen.data.repository.UserRepository
import com.example.loginscreen.ui.viewmodel.getOrAwaitValueTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response
import org.junit.Assert.*

class LoginViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var viewModel: LoginViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = LoginViewModel(userRepository)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun testLoginSuccess() = runTest {
        val mockResponse = Response.success(LoginResponse("abc123"))
        Mockito.`when`(userRepository.login("testuser", "testpassword")).thenReturn(mockResponse)

        viewModel.login("testuser", "testpassword")

        // Avance até que todas as corrotinas sejam executadas
        advanceUntilIdle()

        // Obtenha o resultado do LiveData e verifique se é um sucesso
        val result = viewModel.loginResultLiveData.getOrAwaitValueTest()

        println((result as LoginResult.Success).token)
        assertTrue(result is LoginResult.Success)
        assertEquals("abc123", (result as LoginResult.Success).token)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}