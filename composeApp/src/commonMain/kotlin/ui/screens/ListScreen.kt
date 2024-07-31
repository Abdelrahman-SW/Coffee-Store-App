package ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ui.MainViewModel
import ui.components.AppSearchBar
import ui.components.CategoryTabs
import ui.components.CoffeeList
import ui.components.IconsTopBar
import ui.components.TopBarIcon
import ui.components.WelcomeTopBar
import ui.primaryColor

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel,
    navController: NavController
) {
    val isLoading = mainViewModel.isLoading
    val coffeeList = mainViewModel.coffeeList
    val leftIcon = remember {
        TopBarIcon(Icons.Default.ShoppingCart)
    }
    val rightIcon = remember {
        TopBarIcon(Icons.Default.Menu)
    }
    Column(modifier = modifier.fillMaxSize().padding(vertical = 32.dp , horizontal = 16.dp)) {
        IconsTopBar(leftIcon = leftIcon , rightIcon = rightIcon)
        Spacer(modifier = Modifier.height(32.dp))
        WelcomeTopBar()
        Spacer(modifier = Modifier.height(16.dp))
        AppSearchBar()
        Spacer(modifier = Modifier.height(16.dp))
        CategoryTabs()
        Spacer(modifier = Modifier.height(32.dp))
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center) , color = primaryColor)
            }
        }
        else {
            CoffeeList(coffeeList = coffeeList, navController = navController)
        }
    }
}