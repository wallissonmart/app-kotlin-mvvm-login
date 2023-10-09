import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginscreen.common.LoginResult
import com.example.loginscreen.data.model.LoginResponse
import com.example.loginscreen.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    val loginButtonEnabledLiveData = MutableLiveData<Boolean>(true)
    val progressBarVisibleLiveData = MutableLiveData<Boolean>(false)

    val loginResultLiveData = MutableLiveData<LoginResult>()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                progressBarVisibleLiveData.value = true
                loginButtonEnabledLiveData.value = false

                // Simule uma chamada de API de login
                val response = performLogin(username, password)

                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null && loginResponse.token != null) {
                        // Login bem-sucedido
                        loginResultLiveData.value = LoginResult.Success(loginResponse.token)
                    }
                } else if (response.code() === 401) {
                    // Resposta da API não foi bem-sucedida
                    val errorResponseBody = response.errorBody()?.string()
                    println(errorResponseBody)
                    loginResultLiveData.value = LoginResult.Error("E-mail ou senha inválidos!")
                }
            } catch (e: Exception) {
                // Erro de conexão ou outro erro não tratado
                loginResultLiveData.value = LoginResult.Error("Erro de conexão")
            } finally {
                // Habilitar o botão de login novamente e ocultar o spinner de carregamento
                loginButtonEnabledLiveData.value = true
                progressBarVisibleLiveData.value = false
            }
        }
    }

    private suspend fun performLogin(username: String, password: String): Response<LoginResponse> {
        return withContext(Dispatchers.IO) {
            val response = userRepository.login(username, password)
            response
        }
    }
}
