import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.exo.daily_spikeur.R
import com.exo.daily_spikeur.data.models.User
import com.exo.daily_spikeur.data.repositories.UserRepository
import kotlinx.coroutines.launch

class MainViewModel(val userRepository: UserRepository) : ViewModel() {

    private var _user = mutableStateOf(User("User", "local 1", "#LOCALID", 100, R.drawable.honor_gambier, 5, intArrayOf(1,2,3)))
    val user: State<User> = _user

    fun getUser() {
        viewModelScope.launch {
            val user = userRepository.getUser()
            _user.value = user
        }
    }

    // Simulation de tâches longues si besoin
    init {
        println("viewmodelinit")
        viewModelScope.launch {

        }
    }

    class ViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                return MainViewModel(userRepository = userRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}
