package ru.geekbrains.ponomarevss.sportradarnflnotes.di.module

import dagger.Module
import dagger.Provides
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.api.IDataSource
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IConferencesCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.ISeasonsCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IWeeksCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.network.INetworkStatus
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.retrofit.RetrofitConferencesRepo
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.retrofit.RetrofitGamesRepo
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.retrofit.RetrofitSeasonsRepo
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.retrofit.RetrofitWeeksRepo
import javax.inject.Singleton

@Module
class RepoModule {

    @Provides
    @Singleton
    fun conferencesRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IConferencesCache,
    ): IConferencesRepo = RetrofitConferencesRepo(api, networkStatus, cache)

    @Provides
    @Singleton
    fun seasonsRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: ISeasonsCache,
    ): ISeasonsRepo = RetrofitSeasonsRepo(api, networkStatus, cache)

    @Provides
    @Singleton
    fun weekRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IWeeksCache,
    ): IWeeksRepo = RetrofitWeeksRepo(api, networkStatus, cache)

    @Provides
    @Singleton
    fun gameRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IWeeksCache,
    ): IGamesRepo = RetrofitGamesRepo(api, networkStatus, cache)
}