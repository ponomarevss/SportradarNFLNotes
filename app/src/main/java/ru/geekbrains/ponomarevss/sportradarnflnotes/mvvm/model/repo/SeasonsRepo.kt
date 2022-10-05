package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.repo

import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.api.IDataSource
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.cache.ISeasonsCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.mapReToSeason

class SeasonsRepo(private val api: IDataSource, private val cache: ISeasonsCache) : ISeasonsRepo {

    override suspend fun getCachedSeasons(): List<Season> =
        cache.getSeasons()

    override suspend fun getApiSeasons(): List<Season> {
        val seasons: List<Season> = api.getSeasons().seasons.map { mapReToSeason(it) }
        cache.putSeasons(seasons)
        return seasons
    }

}


