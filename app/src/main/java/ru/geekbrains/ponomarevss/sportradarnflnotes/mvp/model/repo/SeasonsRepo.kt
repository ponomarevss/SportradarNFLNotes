package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.api.IDataSource
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.ISeasonsCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.mapReToSeason

class SeasonsRepo(private val api: IDataSource, private val cache: ISeasonsCache) : ISeasonsRepo {

    override suspend fun getSeasons(isOnline: Boolean): List<Season> {
        var seasons: List<Season>
        withContext(Dispatchers.IO) {
            seasons = cache.getSeasons()
            if (isOnline) {
//                seasons = api.getSeasons().seasons.map { mapReToSeason(it) }
                cache.putSeasons(seasons)
            }
        }
        return seasons
    }

//    override suspend fun getSeasons(isOnline: Boolean): List<Season> {
//        var seasons: List<Season>
//        withContext(Dispatchers.IO) {
//            try {
//                seasons = api.getSeasons().seasons.map { mapReToSeason(it) }
//                cache.putSeasons(seasons)
//            } catch (e: Exception) {
//                seasons = cache.getSeasons()
//            }
//        }
//        return seasons
//    }
}


