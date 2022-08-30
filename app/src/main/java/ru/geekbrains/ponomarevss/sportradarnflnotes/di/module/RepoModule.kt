package ru.geekbrains.ponomarevss.sportradarnflnotes.di.module

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.core.Scheduler
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.api.IDataSource
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Team
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.network.INetworkStatus
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.*
import javax.inject.Singleton

@Module
class RepoModule {

//    @Provides
//    @Singleton
//    fun conferencesRepo(
//        scheduler: Scheduler,
//        api: IDataSource,
//        networkStatus: INetworkStatus,
//        cache: ITeamsCache,
//    ): ITeamsRepo = TeamsRepo(scheduler, api, networkStatus, cache)
//
//    @Provides
//    @Singleton
//    fun seasonsRepo(
//        scheduler: Scheduler,
//        api: IDataSource,
//        networkStatus: INetworkStatus,
//        cache: ISeasonsCache,
//    ): ISeasonsRepo = SeasonsRepo(scheduler, api, networkStatus, cache)
//
//    @Provides
//    @Singleton
//    fun weekRepo(
//        scheduler: Scheduler,
//        api: IDataSource,
//        networkStatus: INetworkStatus,
//        cache: IWeeksCache,
//    ): IWeeksRepo = WeeksRepo(scheduler, api, networkStatus, cache)
//
//    @Provides
//    @Singleton
//    fun gameRepo(
//        scheduler: Scheduler,
//        api: IDataSource,
//        networkStatus: INetworkStatus,
//        cache: IGamesCache,
//    ): IGamesRepo = GamesRepo(scheduler, api, networkStatus, cache)
//
//    @Provides
//    @Singleton
//    fun standingsRepo(
//        scheduler: Scheduler,
//        cache: IStandingsCache,
//        teams: List<Team>
//    ): IStandingsRepo = StandingsRepo(scheduler, cache, teams)
}