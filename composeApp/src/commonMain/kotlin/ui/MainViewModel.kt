package ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.firstTimeKey
import data.nameKey
import domain.CoffeeClient
import domain.CoffeeItem
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    private val coffeeClient: CoffeeClient,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    val firstTimeStateFlow = dataStore.data.map { it ->
        it[firstTimeKey] ?: true
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)


    val usernameStateFlow = dataStore.data.map { it ->
        it[nameKey] ?: ""
    }

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

    fun onGetStartedClicked(name: String) {
        viewModelScope.launch {
            dataStore.edit {
                it[firstTimeKey] = false
                it[nameKey] = name
            }
        }
    }
}