package domain

import kotlinx.serialization.Serializable

@Serializable
object ListScreen


//@Serializable
//data class DetailScreen(val coffeeItem: CoffeeItem)

@Serializable
data class DetailScreen(
    val id: Int,
    val name: String,
    val description: String,
    val price: Float,
    val imageUrl: String
)

fun DetailScreen.toCoffeeItem() = CoffeeItem(
    id = id,
    name = name,
    description = description,
    price = price,
    imageUrl = imageUrl
);

fun CoffeeItem.toDetailScreen() = DetailScreen(
    id = id,
    name = name,
    description = description,
    price = price,
    imageUrl = imageUrl
);