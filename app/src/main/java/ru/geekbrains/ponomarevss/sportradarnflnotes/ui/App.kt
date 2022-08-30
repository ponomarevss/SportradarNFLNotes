package ru.geekbrains.ponomarevss.sportradarnflnotes.ui

import android.app.Application
import org.koin.core.context.startKoin
import ru.geekbrains.ponomarevss.sportradarnflnotes.di.application
import ru.geekbrains.ponomarevss.sportradarnflnotes.di.seasonsFragment

class App : Application() {
    companion object{
        lateinit var instance: App
    }

//    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin { modules(application, seasonsFragment) }

//        appComponent = DaggerAppComponent.builder()
//            .appModule(AppModule(this))
//            .build()
    }
}