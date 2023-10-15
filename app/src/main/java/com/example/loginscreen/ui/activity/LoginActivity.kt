package com.example.loginscreen.ui.activity

import LoginViewModel
import LoginViewModelFactory
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.example.loginscreen.R
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.example.loginscreen.common.LoginResult
import com.example.loginscreen.data.remote.network.RetrofitInstance
import com.example.loginscreen.data.repository.UserRepository

class LoginActivity : AppCompatActivity() {
    private val apiService = RetrofitInstance.create()
    private val userRepository = UserRepository(apiService)
    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(userRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        // Configurar o botão de login
        val loginButton = findViewById<AppCompatButton>(R.id.btn_login)
        // Encontrar as EditTexts pelo ID
        val userOrEmailEditText = findViewById<AppCompatEditText>(R.id.edit_text_user_or_email)
        val passwordEditText = findViewById<AppCompatEditText>(R.id.edit_text_password)

        loginButton.setOnClickListener {
            val username = userOrEmailEditText.text.toString()
            val password = passwordEditText.text.toString()
            viewModel.login(username, password)
        }

        viewModel.loginResultLiveData.observe(this) { result: LoginResult ->
            when (result) {
                is LoginResult.Success -> {
                    // Login bem-sucedido, vá para a próxima tela
                    val token = result.token
                    println(token)
                    // Faça o que for necessário com o token, como salvar em SharedPreferences
                    // e depois vá para a próxima tela
                    startActivity(Intent(this, HomeActivity::class.java))
                    // finish()
                }

                is LoginResult.Error -> {
                    // Exibir uma mensagem de erro ao usuário
                    val errorMessage = result.message
                    Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Observar o estado do botão de login
        viewModel.loginButtonEnabledLiveData.observe(this) { isEnabled ->
            loginButton.isEnabled = isEnabled
        }

        // Observar a visibilidade do spinner de carregamento
        viewModel.progressBarVisibleLiveData.observe(this) { isVisible ->
            progressBar.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
        }
    }
}
