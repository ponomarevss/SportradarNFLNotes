package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.api.IDataSource
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IWeekCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.network.INetworkStatus
import java.util.concurrent.TimeUnit

class RetrofitWeekRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val cache: IWeekCache
) : IWeekRepo {

    override fun getWeek(year: Int, type: String, sequence: Int): Single<Week> =
        cache.getWeek(year, type, sequence)
            .doOnError{
                api.getWeeklySchedule(year.toString(), type, sequence.toString())
                    .flatMap { schedule -> cache.putWeek(schedule)
                        .toSingle{cache.getWeek(year, type, sequence)} }
                    .subscribe()
            }
            .subscribeOn(Schedulers.io())

    override fun getWeeks(year: Int, type: String): Single<List<Week>> {
        TODO("Not yet implemented")
    }
}
