package tech.michalik.mvxexample

import android.app.Application

/**
 * Created by jaroslawmichalik on 2019-12-05
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppModule.attachApp(this)
    }
}