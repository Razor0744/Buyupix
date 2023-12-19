package team.four.mys.app

import android.app.Application
import team.four.mys.di.AppComponent
import team.four.mys.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory().create(this)
    }
}