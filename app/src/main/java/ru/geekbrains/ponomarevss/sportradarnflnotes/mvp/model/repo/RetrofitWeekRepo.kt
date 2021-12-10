package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.api.IDataSource
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IWeekCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.network.INetworkStatus

class RetrofitWeekRepo(
    val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: IWeekCache
) : IWeekRepo {

    override fun getWeek(year: Int, type: String, sequence: Int): Single<Week> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                cache.getWeek(year, type, sequence)
                    .onErrorResumeNext{
                        api.getWeeklySchedule(year.toString(), type, sequence.toString())
                            .flatMap { schedule ->
                                cache.putWeek(schedule)
                                    .toSingle { schedule.week }
                            }
                    }
            } else cache.getWeek(year, type, sequence)
        }
        .subscribeOn(Schedulers.io())

    override fun getWeeks(year: Int, type: String): Single<List<Week>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                cache.getWeeks(year, type)
                    .flatMap {
                        if (it.isEmpty()) {
                            api.getSeasonSchedule(year.toString(), type)
                                .flatMap { schedule ->
                                    cache.putWeeks(schedule)
                                        .toSingle { schedule.weeks }
                                }
                        } else Single.just(it)
                    }
            } else cache.getWeeks(year, type)
        }
        .subscribeOn(Schedulers.io())
}


/**Вариант, когда репо, в случае onError, отдает объект из cache*/
//    .flatMap { schedule ->
//        cache.putWeek(schedule) .toSingle { cache.getWeek(year, type, sequence).blockingGet() }
//    }