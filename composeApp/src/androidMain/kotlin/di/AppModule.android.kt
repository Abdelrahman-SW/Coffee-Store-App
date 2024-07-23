package di

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import networking.clientEngine
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule: Module = module {
    singleOf(::clientEngine)
}