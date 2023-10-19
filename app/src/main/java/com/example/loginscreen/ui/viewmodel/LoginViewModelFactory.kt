import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.loginscreen.data.repository.UserRepositoryImpl

class LoginViewModelFactory(private val userRepository: UserRepositoryImpl) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
