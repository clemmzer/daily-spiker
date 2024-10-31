import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.exo.daily_spikeur.R
import com.exo.daily_spikeur.data.models.RarityEnum
import com.exo.daily_spikeur.data.models.User
import com.exo.daily_spikeur.data.repositories.UserRepository
import kotlinx.coroutines.launch

class MainViewModel(val userRepository: UserRepository) : ViewModel() {

    private var _user = mutableStateOf(User("User", "local 1", "#LOCALID", 100, 1, 5, intArrayOf(1,2,3)))
    val user: State<User> = _user

    fun getUser() {
        viewModelScope.launch {
            val user = userRepository.getUser()
            _user.value = user
        }
    }

    fun changeProfilImage(newPhoto: Int) {
        _user.value = _user.value.copy(photo = newPhoto)
    }

    fun addPooper(newPooper: Int) {
        _user.value = _user.value.copy(poopers = _user.value.poopers.plus(newPooper))
    }

    fun getImageById(imageId: Int): Int {
        var image: Int? = null
        viewModelScope.launch {
            image = userRepository.getImageById(imageId)
        }
        return image ?: R.drawable.base_1
    }

    // Pourrait servir dans le futur pour afficher tous les skins disponible
    fun getImagesIdByRarity(rarity: RarityEnum): IntArray {
        var images: IntArray = intArrayOf()
        viewModelScope.launch {
            images = userRepository.getImagesIdByRarity(rarity)
        }
        return images
    }

    fun openChest(): Int {
        var reward: Int = R.drawable.base_1
        viewModelScope.launch {
            _user.value = _user.value.copy(points = _user.value.points-100) // Sera supprimé lorsque l'API le fera
            reward = userRepository.openChest(_user.value.poopers)
            addPooper(reward) // Sera supprimé lorsque l'API le fera
        }
        return reward
    }

    fun getPoints(): Int {
        return _user.value.points
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
