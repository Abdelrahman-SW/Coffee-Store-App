package ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
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
              DetailScreen(coffeeItem = detailScreen.toCoffeeItem() , navController = navController)
            }
        }
    }
}