import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exo.daily_spikeur.R
import kotlinx.coroutines.launch

class UserProfileViewModel : ViewModel() {

    private val _profileImageResId = mutableStateOf(R.drawable.base_3) // Image par défaut
    val profileImageResId: State<Int> = _profileImageResId

    // Fonction pour changer l'image de profil
    fun changeProfileImage(newImageResId: Int) {
        _profileImageResId.value = newImageResId
    }

    // Simulation de tâches longues si besoin
    init {
        println("viewmodelinit")
        viewModelScope.launch {
            // Ici, tu peux lancer des tâches pour initialiser d'autres données si nécessaire
        }
    }
}
