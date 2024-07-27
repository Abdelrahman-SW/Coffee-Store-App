package ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ui.toDetailScreen
import ui.MainViewModel
import ui.components.CoffeeItem

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel,
    navController: NavController
) {
    val isLoading = mainViewModel.isLoading
    val coffeeList = mainViewModel.coffeeList
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        val name by mainViewModel.getName().collectAsState("No Name")
        var data by rememberSaveable {
            mutableStateOf("")
        }
//        if (isLoading) {
//            CircularProgressIndicator()
//        }
//        else {
//            LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
//                items(coffeeList, key = { it.id }) { item ->
//                    CoffeeItem(coffeeItem = item) {
//                        navController.navigate(it.toDetailScreen())
//                    }
//                }
//            }
//        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Name = $name", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(16.dp))
            TextField(value = data, onValueChange = { data = it })
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { mainViewModel.saveName(data) }) {
                Text(text = "Save")
            }
        }
    }
}