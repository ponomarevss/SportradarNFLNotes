package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.api.IDataSource
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IWeeksCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.mapReToWeek
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.network.INetworkStatus

class WeeksRepo(
    private val ioScheduler: Scheduler,
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: IWeeksCache
) : IWeeksRepo {

    override fun getWeeks(season: Season): Single<List<Week>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            cache.getWeeks(season.id)
                .flatMap { cachedWeeks ->
                    if (cachedWeeks.isEmpty()) {
                        api.getSeasonSchedule(season.year.toString(), season.type)
                            .flatMap { schedule ->
                                val weeks = schedule.weeks.map { mapReToWeek(it) }
                                cache.putWeeks(weeks, season).toSingle { weeks }
                            }
                    } else throw Throwable("Unable to fetch weeks")
                }
        } else cache.getWeeks(season.id)
    }.subscribeOn(ioScheduler)
}