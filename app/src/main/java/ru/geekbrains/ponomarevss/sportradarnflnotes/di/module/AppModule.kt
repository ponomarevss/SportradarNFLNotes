package ru.geekbrains.ponomarevss.sportradarnflnotes.di.module

import dagger.Module
import dagger.Provides
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.App

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App = app
}