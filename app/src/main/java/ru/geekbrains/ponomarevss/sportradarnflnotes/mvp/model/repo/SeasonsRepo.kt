package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.api.IDataSource
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.ISeasonsCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.mapReToSeason
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.network.INetworkStatus
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.ISeasonsRepo

class SeasonsRepo() : ISeasonsRepo {

//class RetrofitSeasonsRepo(
//    private val ioScheduler: Scheduler,
//    private val api: IDataSource,
//    private val networkStatus: INetworkStatus,
//    private val cache: ISeasonsCache
//) : ISeasonsRepo {

    override fun getSeasons(): List<Season> = listOf(
        Season("1", 2019, "closed", "REG"),
        Season("2", 2020, "closed", "REG"),
        Season("3", 2021, "closed", "REG"),
        Season("4", 2022, "closed", "REG")
    )
//    override fun getSeasons(): Single<List<Season>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
//        if (isOnline) {
//            api.getSeasons()
//                .flatMap { reLeagueSeasons ->
//                    val seasons = reLeagueSeasons.seasons.map { mapReToSeason(it) }
//                    cache.putSeasons(seasons).toSingle { seasons }
//                }
//        } else cache.getSeasons()
//    }.subscribeOn(ioScheduler)
}


