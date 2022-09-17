package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.api.IDataSource
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IWeeksCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.mapReToWeek

class WeeksRepo(private val api: IDataSource, private val cache: IWeeksCache) : IWeeksRepo {

    companion object {
        private const val REQUESTS_GAP = 2000L
    }

    override suspend fun getCachedWeeks(season: Season): List<Week> =
//        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
//            cache.getWeeks(season.id)
//        }
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            Log.e("AAA", "season repo.getCachedWeeks got to get weeks: $season")
            cache.getWeeks(season.id)
        }.also { Log.e("AAA", "WeeksRepo.getCachedWeeks: $it") }

    override suspend fun getApiWeeks(season: Season): List<Week> =
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            delay(REQUESTS_GAP)
            val weeks: List<Week> =
                api.getSeasonSchedule(season.year.toString(), season.type).weeks.map {
                    mapReToWeek(it)
                }
            cache.putWeeks(weeks, season)
            weeks
        }
}