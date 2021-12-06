package ru.geekbrains.ponomarevss.sportradarnflnotes.di.module

import dagger.Module
import dagger.Provides
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.api.IDataSource
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IWeekCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.network.INetworkStatus
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.IWeekRepo
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.RetrofitWeekRepo
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepoModule {

    @Provides
    @Singleton
    fun weekRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IWeekCache,
    ): IWeekRepo = RetrofitWeekRepo(api, networkStatus, cache)

}