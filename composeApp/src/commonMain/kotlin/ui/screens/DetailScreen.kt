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
import domain.CoffeeItem
import domain.DetailScreen
import ui.components.CoffeeDetailsItem
import ui.components.CoffeeItem

@Composable
fun DetailScreen(modifier: Modifier = Modifier , coffeeItem: CoffeeItem) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CoffeeDetailsItem(coffeeItem = coffeeItem)
    }
}