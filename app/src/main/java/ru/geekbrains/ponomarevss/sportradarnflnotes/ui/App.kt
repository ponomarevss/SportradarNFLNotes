package ru.geekbrains.ponomarevss.sportradarnflnotes.ui

import android.app.Application
import org.koin.core.context.startKoin
import ru.geekbrains.ponomarevss.sportradarnflnotes.di.AppComponent
import ru.geekbrains.ponomarevss.sportradarnflnotes.di.module.AppModule
import ru.geekbrains.ponomarevss.sportradarnflnotes.di.module.application

class App : Application() {
    companion object{
        lateinit var instance: App
    }

//    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin { modules(application) }

//        appComponent = DaggerAppComponent.builder()
//            .appModule(AppModule(this))
//            .build()
    }
}