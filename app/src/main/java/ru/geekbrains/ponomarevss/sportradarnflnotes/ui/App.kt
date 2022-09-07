package ru.geekbrains.ponomarevss.sportradarnflnotes.ui

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.geekbrains.ponomarevss.sportradarnflnotes.di.application
import ru.geekbrains.ponomarevss.sportradarnflnotes.di.seasonsFragment

class App : Application() {
    companion object{
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidContext(applicationContext)
            modules(application, seasonsFragment)
        }
    }
}