package ru.geekbrains.ponomarevss.sportradarnflnotes.ui

import android.app.Application
import ru.geekbrains.ponomarevss.sportradarnflnotes.di.AppComponent
import ru.geekbrains.ponomarevss.sportradarnflnotes.di.DaggerAppComponent
import ru.geekbrains.ponomarevss.sportradarnflnotes.di.module.AppModule

class App : Application() {
    companion object{
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}