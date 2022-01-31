package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.retrofit

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.api.IDataSource
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.ISeasonsCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.network.INetworkStatus
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.ISeasonsRepo

class RetrofitSeasonsRepo(val api: IDataSource, private val networkStatus: INetworkStatus, private val cache: ISeasonsCache) : ISeasonsRepo {

    override fun getSeasons(): Single<List<Season>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getSeasons()
                .flatMap {
                    cache.putSeasons(it.seasons).toSingle { it.seasons }
                }
        } else cache.getSeasons()
    }.subscribeOn(Schedulers.io())
}


