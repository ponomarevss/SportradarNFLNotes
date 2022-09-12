package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.api.IDataSource
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.ISeasonsCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.mapReToSeason

class SeasonsRepo(private val api: IDataSource, private val cache: ISeasonsCache) : ISeasonsRepo {

    override suspend fun getSeasons(isOnline: Boolean): List<Season> =
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            val seasons: List<Season>
            if (isOnline) {
                seasons = api.getSeasons().seasons.map { mapReToSeason(it) }
                cache.putSeasons(seasons)
            } else {
                seasons = cache.getSeasons()
            }
            seasons
        }
}


