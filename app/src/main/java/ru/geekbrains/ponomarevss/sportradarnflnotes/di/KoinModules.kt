package ru.geekbrains.ponomarevss.sportradarnflnotes.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.navigation.IScreens
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.ISeasonsRepo
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.SeasonsRepo
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.navigation.AndroidScreens
import ru.geekbrains.ponomarevss.sportradarnflnotes.viewmodel.SeasonsViewModel

val application = module {
    single { Cicerone.create() }
    single { get<Cicerone<Router>>().getNavigatorHolder() }
    single { get<Cicerone<Router>>().router }
    single<IScreens> { AndroidScreens() }
}

val seasonsFragment = module {
    single<ISeasonsRepo> { SeasonsRepo() }
    viewModel { SeasonsViewModel(get()) }
}