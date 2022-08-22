package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.retrofit

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.api.IDataSource
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IWeeksCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.oldcommon.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.oldcommon.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.network.INetworkStatus
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.IWeeksRepo

class RetrofitWeeksRepo(val api: IDataSource, private val networkStatus: INetworkStatus, private val cache: IWeeksCache) : IWeeksRepo {

    override fun getWeeks(season: Season): Single<List<Week>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            cache.getWeeks(season.id)
                .flatMap {
                    if (it.isEmpty()) {
                        api.getSeasonSchedule(season.year.toString(), season.type.code)
                            .flatMap { schedule ->
                                cache.putWeeks(
                                    schedule.scheduleWeeks.map { scheduleWeek -> scheduleWeek.toWeek() },
                                    season.id
                                ).toSingle {
                                    schedule.scheduleWeeks.map { scheduleWeek ->
                                        scheduleWeek.toWeek()
                                    }
                                }
                            }
                    } else Single.just(it)
                }
        } else cache.getWeeks(season.id)
    }.subscribeOn(Schedulers.io())
}