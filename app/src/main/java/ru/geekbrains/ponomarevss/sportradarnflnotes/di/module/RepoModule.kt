package ru.geekbrains.ponomarevss.sportradarnflnotes.di.module

import dagger.Module
import dagger.Provides
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.api.IDataSource
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.network.INetworkStatus
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.retrofit.*
import javax.inject.Singleton

@Module
class RepoModule {

    @Provides
    @Singleton
    fun conferencesRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: ITeamsCache,
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
        cache: IGamesCache,
    ): IGamesRepo = RetrofitGamesRepo(api, networkStatus, cache)

    @Provides
    @Singleton
    fun standingsRepo(
        api: IDataSource,
        cache: IStandingsCache,
        teamsCache: ITeamsCache
    ): IStandingsRepo = RetrofitStandingsRepo(api, cache, teamsCache)
}