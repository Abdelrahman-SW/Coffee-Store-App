package ui.screens

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ListScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel,
    navController: NavController,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    val username = mainViewModel.usernameStateFlow.collectAsState("")
    val searchQuery by mainViewModel.searchQuery.collectAsState("")
    val isLoading = mainViewModel.isLoading
    val coffeeList by mainViewModel.coffeeList.collectAsState(emptyList())
    val leftIcon = remember {
        TopBarIcon(Icons.Default.ShoppingCart)
    }
    val rightIcon = remember {
        TopBarIcon(Icons.Default.Menu)
    }
    Column(modifier = modifier.fillMaxSize().padding(vertical = 32.dp , horizontal = 16.dp) , horizontalAlignment = Alignment.CenterHorizontally) {
        IconsTopBar(leftIcon = leftIcon , rightIcon = rightIcon)
        Spacer(modifier = Modifier.height(32.dp))
        WelcomeTopBar(username = username.value)
        Spacer(modifier = Modifier.height(16.dp))
        AppSearchBar(query = searchQuery) {
            mainViewModel.onSearchQueryChanged(it)
        }
        Spacer(modifier = Modifier.height(16.dp))
        CategoryTabs()
        Spacer(modifier = Modifier.height(24.dp))
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center) , color = primaryColor)
            }
        }
        else {
            CoffeeList(
                coffeeList = coffeeList,
                navController = navController,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                animatedVisibilityScope = animatedVisibilityScope
            )
        }
    }
}