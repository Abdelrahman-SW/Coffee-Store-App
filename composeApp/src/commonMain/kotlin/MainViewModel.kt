import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.CoffeeClient
import domain.CoffeeItem
import kotlinx.coroutines.launch

class MainViewModel(
    private val coffeeClient: CoffeeClient
) : ViewModel() {
    var isLoading by mutableStateOf(false)
        private set

    var coffeeList by mutableStateOf(emptyList<CoffeeItem>())
        private set


    init {
        viewModelScope.launch {
            isLoading = true
            coffeeList = coffeeClient.getAllCoffeesItems()
            isLoading = false
        }
    }
}