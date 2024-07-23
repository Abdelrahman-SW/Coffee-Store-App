package di

import MainViewModel
import data.networking.CoffeeClientKtorImpl
import domain.CoffeeClient
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.compose.viewmodel.dsl.viewModelOf

expect val platformModule: Module

val sharedModule = module {
    single {
        HttpClient(engine = get()) {
            install(Logging) {
                level = LogLevel.BODY
            }
            install(ContentNegotiation){
                json(json = Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    singleOf(::CoffeeClientKtorImpl).bind<CoffeeClient>()

    viewModelOf(::MainViewModel)
}