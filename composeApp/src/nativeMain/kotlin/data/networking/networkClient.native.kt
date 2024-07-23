package data.networking

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin

actual val clientEngine: HttpClientEngine = Darwin.create()