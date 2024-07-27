package data

import androidx.datastore.preferences.core.stringPreferencesKey


internal const val DATA_STORE_FILE_NAME = "coffee_app.preferences_pb"

val nameDataStoreKey = stringPreferencesKey("name")

