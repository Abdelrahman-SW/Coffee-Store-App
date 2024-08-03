package ui

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import ui.screens.WelcomeScreen

@OptIn(KoinExperimentalAPI::class, ExperimentalSharedTransitionApi::class)
@Composable
@Preview
fun App() {
    KoinContext {
        val mainViewModel = koinViewModel<MainViewModel>()
        val navController = rememberNavController()
        val firstTimeState by mainViewModel.firstTimeStateFlow.collectAsState(false)
        SharedTransitionLayout {
            NavHost(navController = navController, startDestination = if (firstTimeState) WelcomeScreen else ListScreen) {
                composable<WelcomeScreen> {
                    WelcomeScreen(
                        viewModel = mainViewModel,
                        navController = navController,
                    )
                }

                composable<ListScreen> {
                    ListScreen(
                        mainViewModel = mainViewModel,
                        navController = navController,
                        animatedVisibilityScope = this
                    )
                }

                composable<DetailScreen> { it ->
                    val detailScreen = it.toRoute<DetailScreen>()
                    DetailScreen(
                        coffeeItem = detailScreen.toCoffeeItem(),
                        navController = navController,
                        animatedVisibilityScope = this
                    )
                }
            }
        }
    }
}