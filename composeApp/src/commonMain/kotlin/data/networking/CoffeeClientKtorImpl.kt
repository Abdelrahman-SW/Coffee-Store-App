package data.networking

import data.dto.CoffeeItemDTO
import data.mappers.toCoffeeItem
import domain.CoffeeClient
import domain.CoffeeItem
import domain.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class CoffeeClientKtorImpl(
    private val httpClient: HttpClient
) : CoffeeClient {
    override suspend fun getAllCoffeesItems(): List<CoffeeItem> {
        val response = try {
            httpClient.get(Constants.COFFEE_API_URL) {}
        } catch (e: Exception) {
            println("getAllCoffeesItems Error ${e.message}")
            return emptyList()
        }
        return when (response.status.value) {
            in 200..299 -> {
                val coffeeDtoList = response.body<List<CoffeeItemDTO>>()
                coffeeDtoList.map { it.toCoffeeItem() }
            }
            else -> {
                emptyList()
            }
        }
    }
}