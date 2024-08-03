package ui.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coffeeapp.composeapp.generated.resources.Res
import coffeeapp.composeapp.generated.resources.baseline_sort_24
import domain.CoffeeItem
import org.jetbrains.compose.resources.painterResource
import ui.GetPoppinsFamily
import ui.primaryColor
import ui.primaryColorAlpha
import ui.toDetailScreen


data class TopBarIcon(
    val icon: ImageVector,
    val tint: Color = Color.White,
    val onClick: () -> Unit = {}
)

@Composable
fun IconsTopBar(modifier: Modifier = Modifier, leftIcon: TopBarIcon, rightIcon: TopBarIcon) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = primaryColor,
                    shape = RoundedCornerShape(16.dp)
                ),
            onClick = leftIcon.onClick
        ) {
            Icon(imageVector = leftIcon.icon, contentDescription = "", tint = leftIcon.tint)
        }

        IconButton(
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = primaryColor,
                    shape = RoundedCornerShape(16.dp)
                ),
            onClick = rightIcon.onClick

        ) {
            Icon(imageVector = rightIcon.icon, contentDescription = "", tint = rightIcon.tint)
        }
    }
}

@Composable
fun WelcomeTopBar(modifier: Modifier = Modifier, username: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Welcome  \uD83D\uDC4B",
            fontSize = 18.sp,
            color = Color.Gray,
            fontFamily = GetPoppinsFamily()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = username,
            fontSize = 24.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontFamily = GetPoppinsFamily()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppSearchBar(modifier: Modifier = Modifier) {
    SearchBar(
        colors = SearchBarDefaults.colors(containerColor = primaryColorAlpha),
        //modifier = modifier.padding(horizontal = 16.dp),
        query = "",
        onQueryChange = {},
        onSearch = {},
        active = false,
        onActiveChange = {},
        placeholder = {
            Text(text = "Search For Your Coffee", fontFamily = GetPoppinsFamily())
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "")
        },
        trailingIcon = {
            Icon(
                painter = painterResource(resource = Res.drawable.baseline_sort_24),
                contentDescription = ""
            )
        }
    ) {

    }
}

@Composable
fun CategoryTabs(modifier: Modifier = Modifier) {
    val categories = listOf("All", "Cappuccino", "Espresso", "Latte", "Flat White")
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }
    ScrollableTabRow(
        edgePadding = 0.dp,
        selectedTabIndex = selectedIndex,
        modifier = modifier
            .fillMaxWidth()
        //.padding(top = 16.dp),
        ,
        containerColor = Color.Transparent,
        indicator = {},
        divider = {}
    ) {
        categories.forEachIndexed { index, category ->
            Tab(
                selected = selectedIndex == index,
                onClick = { selectedIndex = index },
                selectedContentColor = primaryColor, // Set the color for the selected tab
                unselectedContentColor = Color.Gray // Set the color for the unselected tabs
            ) {
                Column {
                    Text(text = category, fontFamily = GetPoppinsFamily())
                    if (index == selectedIndex) {
                        HorizontalDivider(
                            color = Color.Blue,
                            thickness = 2.dp,
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.CoffeeList(
    modifier: Modifier = Modifier,
    coffeeList: List<CoffeeItem>,
    navController: NavController,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        modifier = modifier.fillMaxSize(),
    ) {
        items(coffeeList, key = { it.id }) { item ->
            CoffeeItem(coffeeItem = item , animatedVisibilityScope = animatedVisibilityScope) {
                navController.navigate(it.toDetailScreen())
            }
        }
    }
}