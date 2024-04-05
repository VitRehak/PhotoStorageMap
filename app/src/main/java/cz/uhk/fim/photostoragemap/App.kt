package cz.uhk.fim.photostoragemap

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import cz.uhk.fim.photostoragemap.dependenciInjection.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.Koin
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            androidLogger(
                Level.DEBUG
            )
            modules(appModules)
        }
    }
}