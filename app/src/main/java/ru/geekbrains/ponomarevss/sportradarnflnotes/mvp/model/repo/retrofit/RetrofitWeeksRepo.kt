package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.retrofit

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.api.IDataSource
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IWeeksCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.network.INetworkStatus
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.IWeeksRepo

class RetrofitWeeksRepo(
    val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: IWeeksCache
) : IWeeksRepo {

    override fun getWeeks(year: Int, type: String): Single<List<Week>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            cache.getWeeksCache(year, type)
                .flatMap {
                    if (it.isEmpty()) {
                        api.getSeasonSchedule(year.toString(), type)
                            .flatMap { schedule ->
                                cache.putWeeksCache(schedule)
                                    .toSingle { schedule.weeks }
                            }
                    } else Single.just(it)
                }
        } else cache.getWeeksCache(year, type)
    }.subscribeOn(Schedulers.io())
}