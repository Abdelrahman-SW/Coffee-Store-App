package ui.screens

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import domain.CoffeeItem
import ui.GetPoppinsFamily
import ui.components.PriceAndBuyNowBar
import ui.components.SizeButtons
import ui.components.TopBarImageWithIcons

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DetailScreen(
    modifier: Modifier = Modifier,
    coffeeItem: CoffeeItem,
    navController: NavController,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Column(modifier = modifier.fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState())) {
        TopBarImageWithIcons(navController = navController , coffeeItem = coffeeItem, animatedVisibilityScope = animatedVisibilityScope)
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                modifier = Modifier.sharedBounds(
                    sharedContentState = rememberSharedContentState("text-${coffeeItem.id}"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = {_,_->
                        tween(700)
                    }
                ),
                fontFamily = GetPoppinsFamily(),
                text = coffeeItem.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                fontFamily = GetPoppinsFamily(),
                text = "With Oat Milk",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                fontFamily = GetPoppinsFamily(),
                text = "Description",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                fontFamily = GetPoppinsFamily(),
                text = coffeeItem.description,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                fontFamily = GetPoppinsFamily(),
                text = "Size",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))
            SizeButtons()
            Spacer(modifier = Modifier.height(16.dp))
            PriceAndBuyNowBar(coffeeItem = coffeeItem)
        }
    }
}