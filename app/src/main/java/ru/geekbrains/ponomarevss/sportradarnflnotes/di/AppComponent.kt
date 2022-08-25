package ru.geekbrains.ponomarevss.sportradarnflnotes.di

import dagger.Component
import ru.geekbrains.ponomarevss.sportradarnflnotes.di.module.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.activity.MainActivity
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        CacheModule::class,
        CiceroneModule::class,
        RepoModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(seasonsPresenter: SeasonsPresenter)
    fun inject(seasonPresenter: SeasonPresenter)
    fun inject(gamesPresenter: GamesPresenter)
}