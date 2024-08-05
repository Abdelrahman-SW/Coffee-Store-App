package ui.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coffeeapp.composeapp.generated.resources.Res
import coffeeapp.composeapp.generated.resources.coffee
import coffeeapp.composeapp.generated.resources.latte
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import domain.CoffeeItem
import org.jetbrains.compose.resources.painterResource
import ui.GetPoppinsFamily
import ui.primaryColor
import ui.primaryColorAlpha


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.TopBarImageWithIcons(
    modifier: Modifier = Modifier,
    coffeeItem: CoffeeItem,
    navController: NavController,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    val leftIcon = remember {
        TopBarIcon(Icons.AutoMirrored.Filled.KeyboardArrowLeft , tint = Color.White) {
            navController.navigateUp()
        }
    }
    val rightIcon = remember {
        TopBarIcon(Icons.Default.Favorite , tint = Color.White)
    }

    Box {
        CoilImage(
            modifier = Modifier
                .sharedElement(
                    state = rememberSharedContentState("image-${coffeeItem.id}"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = {_,_->
                        tween(700)
                    }
                )
                .fillMaxWidth()
                .fillMaxHeight(0.55f)
                .heightIn(max = 500.dp)
                .clip(RoundedCornerShape(32.dp)),
            imageModel = { coffeeItem.imageUrl }, // loading a network image or local resource using an URL.
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            ),
            loading = {
                Box(modifier = Modifier.matchParentSize() , contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = primaryColor)
                }
            }
        )

//        Image(
//            painter = painterResource(resource = Res.drawable.coffee),
//            contentDescription = "",
//            modifier = Modifier
//                .sharedElement(
//                    state = rememberSharedContentState("image-${coffeeItem.id}"),
//                    animatedVisibilityScope = animatedVisibilityScope,
//                    boundsTransform = {_,_->
//                        tween(700)
//                    }
//                )
//                .fillMaxWidth()
//                .fillMaxHeight(0.55f)
//                .heightIn(max = 500.dp)
//                .clip(RoundedCornerShape(32.dp)),
//            contentScale = ContentScale.Crop,
//            alignment = Alignment.Center
//        )

        IconsTopBar(modifier = Modifier.padding(horizontal = 8.dp , vertical = 16.dp) , leftIcon = leftIcon , rightIcon = rightIcon)
    }
}

@Composable
fun SizeButtons(modifier: Modifier = Modifier) {
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }
    val sizes = remember {
        listOf("S", "M", "L")
    }
    Row(
        modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
    ) {
        sizes.forEachIndexed { index, size ->
            Button(
                modifier = Modifier.width(100.dp).height(40.dp).padding(end = 16.dp),
                shape = RoundedCornerShape(8.dp),
                onClick = { selectedIndex = index },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (index == selectedIndex) primaryColor else primaryColorAlpha
                )
            ) {
                Text(text = size , color = Color.Black , fontFamily = GetPoppinsFamily(),
                )
            }
        }
    }
}

@Composable
fun PriceAndBuyNowBar(modifier: Modifier = Modifier , coffeeItem: CoffeeItem) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = buildAnnotatedString {
                pushStyle(
                    SpanStyle(
                        color = primaryColor,
                        fontSize = 24.sp,
                        fontFamily = GetPoppinsFamily()

                        )
                )
                append("$ ")
                pop()
                append(coffeeItem.price.toString())
            },
            fontSize = 20.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )

        Button(
            shape = RoundedCornerShape(16.dp),
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
            modifier = Modifier.width(200.dp).height(50.dp)
        ) {
            Text(text = "Buy Now" , fontSize = 20.sp , color = Color.White , fontFamily = GetPoppinsFamily())
        }
    }
}