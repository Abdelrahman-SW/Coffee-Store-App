package ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coffeeapp.composeapp.generated.resources.Res
import coffeeapp.composeapp.generated.resources.coffee
import coffeeapp.composeapp.generated.resources.latte
import domain.CoffeeItem
import org.jetbrains.compose.resources.painterResource
import ui.GetPoppinsFamily
import ui.primaryColor

@Composable
fun CoffeeItem(
    modifier: Modifier = Modifier,
    coffeeItem: CoffeeItem,
    onItemClicked: (CoffeeItem) -> Unit
) {
    Card(
        modifier = Modifier.width(150.dp).padding(8.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Column(modifier = Modifier.clickable { onItemClicked(coffeeItem) }.padding(16.dp)) {
            Image(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(20.dp))
                    .fillMaxWidth()
                    .height(100.dp)
                    .align(Alignment.CenterHorizontally)
                ,
                contentScale = ContentScale.Crop,
                painter = painterResource(resource = Res.drawable.coffee),
                contentDescription = "Coffee Image"
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                fontFamily = GetPoppinsFamily(),
                text = coffeeItem.name,
                fontSize = 16.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                fontFamily = GetPoppinsFamily(),
                text = "With Oat Milk",
                fontSize = 16.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(4.dp))


            Text(
                text = buildAnnotatedString {
                    pushStyle(
                        SpanStyle(
                            fontFamily = GetPoppinsFamily(),
                            color = primaryColor,
                            fontSize = 20.sp
                        )
                    )
                    append("$ ")
                    pop()
                    append(coffeeItem.price.toString())
                },
                fontSize = 16.sp,
                color = Color.Black,
                fontFamily = GetPoppinsFamily()
                )
        }
    }
}
