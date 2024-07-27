package ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.nameDataStoreKey
import domain.CoffeeClient
import domain.CoffeeItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainViewModel(
    private val coffeeClient: CoffeeClient,
    private val dataStore: DataStore<Preferences>
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

    fun saveName(name: String) {
        viewModelScope.launch {
            dataStore.edit { it ->
                it[nameDataStoreKey] = name
            }
        }
    }

    fun getName(): Flow<String>  {
        return  dataStore.data.map { it[nameDataStoreKey] ?: "No Name"}
    }
}