package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo

import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.api.IDataSource
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.ISeasonsCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.mapReToSeason

class SeasonsRepo(private val api: IDataSource, private val cache: ISeasonsCache) : ISeasonsRepo {

//    override suspend fun getSeasons(): List<Season> = listOf(
//        Season("1", 2019, "closed", "REG"),
//        Season("2", 2020, "closed", "REG"),
//        Season("3", 2021, "closed", "REG"),
//        Season("4", 2022, "closed", "REG")
//    )
//    override fun getSeasons(): Single<List<Season>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
//        if (isOnline) {
//            api.getSeasons()
//                .flatMap { reLeagueSeasons ->
//                    val seasons = reLeagueSeasons.seasons.map { mapReToSeason(it) }
//                    cache.putSeasons(seasons).toSingle { seasons }
//                }
//        } else cache.getSeasons()
//    }.subscribeOn(ioScheduler)

    override suspend fun getSeasons(): List<Season> =
        api.getSeasons().seasons.map { mapReToSeason(it) }
}


