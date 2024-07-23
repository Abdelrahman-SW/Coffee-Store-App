package domain

interface CoffeeClient {
    suspend fun getAllCoffeesItems () : List<CoffeeItem>
}