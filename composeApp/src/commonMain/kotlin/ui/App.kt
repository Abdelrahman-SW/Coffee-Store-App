package ui

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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import domain.CoffeeItem
import ui.components.CoffeeItem
import domain.DetailScreen
import domain.ListScreen
import domain.toCoffeeItem
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import ui.screens.DetailScreen
import ui.screens.ListScreen

@OptIn(KoinExperimentalAPI::class)
@Composable
@Preview
fun App() {
    KoinContext {
        val mainViewModel = koinViewModel<MainViewModel>()
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = ListScreen) {
            composable<ListScreen> {
              ListScreen(mainViewModel = mainViewModel , navController = navController)
            }

            composable<DetailScreen> { it->
              val detailScreen = it.toRoute<DetailScreen>()
              DetailScreen(coffeeItem = detailScreen.toCoffeeItem())
            }
        }
    }
}