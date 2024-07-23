package domain

import kotlinx.serialization.Serializable


@Serializable
data class CoffeeItem(
    val id: Int,
    val name: String,
    val description: String,
    val price: Float,
    val imageUrl: String
)
