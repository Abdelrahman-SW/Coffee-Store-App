package ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import domain.CoffeeItem
import ui.components.CoffeeDetailsItem

@Composable
fun DetailScreen(modifier: Modifier = Modifier , coffeeItem: CoffeeItem , navController: NavController) {
    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        CoffeeDetailsItem(coffeeItem = coffeeItem)
        Spacer(Modifier.height(32.dp))
        Button(onClick = { navController.navigateUp() }) {
            Text("Back")
        }
    }
}