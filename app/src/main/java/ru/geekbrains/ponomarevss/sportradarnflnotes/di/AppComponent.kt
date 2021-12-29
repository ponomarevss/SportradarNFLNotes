package ru.geekbrains.ponomarevss.sportradarnflnotes.di

import dagger.Component
import ru.geekbrains.ponomarevss.sportradarnflnotes.di.module.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.activity.MainActivity
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.fragment.SeasonsFragment
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
    fun inject(weeksPresenter: WeeksPresenter)
    fun inject(gamesPresenter: GamesPresenter)
    fun inject(gamePresenter: GamePresenter)
    fun inject(tablePresenter: TablePresenter)
}