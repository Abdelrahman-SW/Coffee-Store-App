package components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.CoffeeItem

@Composable
fun CoffeeItem(modifier: Modifier = Modifier , coffeeItem: CoffeeItem) {
    Text(text = coffeeItem.name , modifier = modifier.padding(16.dp))
}