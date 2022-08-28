package ru.geekbrains.ponomarevss.sportradarnflnotes.di.module

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import org.koin.dsl.module
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.navigation.IScreens
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.navigation.AndroidScreens

val application = module {
    single { Cicerone.create() }
    single { get<Cicerone<Router>>().getNavigatorHolder() }
    single { get<Cicerone<Router>>().router }
    single<IScreens> { AndroidScreens() }
}