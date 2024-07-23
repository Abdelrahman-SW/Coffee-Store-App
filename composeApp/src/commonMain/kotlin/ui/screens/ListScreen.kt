package ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ui.toDetailScreen
import ui.MainViewModel
import ui.components.CoffeeItem

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel,
    navController: NavController
) {
    val isLoading = mainViewModel.isLoading
    val coffeeList = mainViewModel.coffeeList
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                items(coffeeList, key = { it.id }) { item ->
                    CoffeeItem(coffeeItem = item) {
                        navController.navigate(it.toDetailScreen())
                    }
                }
            }
        }
    }
}