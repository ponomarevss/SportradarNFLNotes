package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.retrofit

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.api.IDataSource
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.ISeasonsCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.mapReToSeason
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.network.INetworkStatus
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.ISeasonsRepo

class RetrofitSeasonsRepo(
    private val ioScheduler: Scheduler,
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: ISeasonsCache
) : ISeasonsRepo {

    override fun getSeasons(): Single<List<Season>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getSeasons()
                .flatMap { reLeagueSeasons ->
                    cache.putSeasons(reLeagueSeasons.seasons.map { mapReToSeason(it) })
                        .toSingle { reLeagueSeasons.seasons.map { mapReToSeason(it) } }
                }
        } else cache.getSeasons()
    }.subscribeOn(ioScheduler)
}


