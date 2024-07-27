package di

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import data.DATA_STORE_FILE_NAME
import data.networking.clientEngine
import okio.Path.Companion.toPath
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformModule: Module = module {
    singleOf(::clientEngine)
    single {
        PreferenceDataStoreFactory.createWithPath {
            val directory = NSFileManager.defaultManager.URLForDirectory(
                directory = NSDocumentDirectory,
                inDomain = NSUserDomainMask,
                appropriateForURL = null,
                create = false,
                error = null
            )
            requireNotNull(directory).path + "/$DATA_STORE_FILE_NAME".toPath()
        }
    }
}