package networking

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

actual val clientEngine: HttpClientEngine = OkHttp.create()