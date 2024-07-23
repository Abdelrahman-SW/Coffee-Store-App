package com.beapps.kmm_cofee_app

import android.app.Application
import di.initKoin
import io.ktor.http.ContentType
import org.koin.android.ext.koin.androidContext

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MyApp)
        }
    }
}