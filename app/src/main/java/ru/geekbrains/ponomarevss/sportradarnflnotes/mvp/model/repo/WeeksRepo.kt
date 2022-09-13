package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.api.IDataSource
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IWeeksCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.mapReToWeek

class WeeksRepo(private val api: IDataSource, private val cache: IWeeksCache) : IWeeksRepo {

//    override fun getWeeks(season: Season): Single<List<Week>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
//        if (isOnline) {
//            cache.getWeeks(season.id)
//                .flatMap { cachedWeeks ->
//                    if (cachedWeeks.isEmpty()) {
//                        api.getSeasonSchedule(season.year.toString(), season.type)
//                            .flatMap { schedule ->
//                                val weeks = schedule.weeks.map { mapReToWeek(it) }
//                                cache.putWeeks(weeks, season).toSingle { weeks }
//                            }
//                    } else throw Throwable("Unable to fetch weeks")
//                }
//        } else cache.getWeeks(season.id)
//    }.subscribeOn(ioScheduler)

    override suspend fun getCachedWeeks(season: Season): List<Week> =
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            cache.getWeeks(season.id)
        }

    override suspend fun getApiWeeks(season: Season): List<Week> =
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            val weeks: List<Week> =
                api.getSeasonSchedule(season.year.toString(), season.type).weeks.map {
                    mapReToWeek(it)
                }
            cache.putWeeks(weeks, season)
            weeks
        }
}