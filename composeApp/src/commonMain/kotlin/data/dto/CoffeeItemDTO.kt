package data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoffeeItemDTO(
    val _id: String,
    val description: String,
    val flavor_profile: List<String>,
    val grind_option: List<String>,
    val id: Int,
    val image_url: String,
    val name: String,
    val price: Double,
    val region: String,
    val roast_level: Int,
    val weight: Int
)