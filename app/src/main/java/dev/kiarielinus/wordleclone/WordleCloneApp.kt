package dev.kiarielinus.wordleclone

import android.app.Application
import dev.kiarielinus.wordleclone.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WordleCloneApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@WordleCloneApp)
            modules(appModule)
        }
    }
}