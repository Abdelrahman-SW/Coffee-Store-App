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
import components.CoffeeItem
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
@Preview
fun App() {
    KoinContext {
        val mainViewModel = koinViewModel<MainViewModel>()
        val isLoading = mainViewModel.isLoading
        val coffeeList = mainViewModel.coffeeList

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            if (isLoading) {
                CircularProgressIndicator()
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                    items(coffeeList , key = {it.id}) { item ->
                        CoffeeItem(coffeeItem = item)
                    }
                }
            }
        }
    }
}