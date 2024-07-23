package ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.CoffeeItem

@Composable
fun CoffeeDetailsItem(modifier: Modifier = Modifier , coffeeItem: CoffeeItem) {
    Text(text = coffeeItem.description , modifier = modifier.padding(16.dp))
}