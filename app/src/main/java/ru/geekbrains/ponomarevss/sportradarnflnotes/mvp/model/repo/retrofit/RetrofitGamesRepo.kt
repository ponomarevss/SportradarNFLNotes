package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.retrofit

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.api.IDataSource
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IWeeksCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Game
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.network.INetworkStatus
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.IGamesRepo

class RetrofitGamesRepo(
    val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: IWeeksCache
) : IGamesRepo {

    override fun getGames(season: Season, week: Week): Single<List<Game>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getWeeklySchedule(season.year.toString(), season.type.code, week.sequence.toString())
                .flatMap { schedule ->
                    cache.putGamesCache(schedule).toSingle { schedule.week.games }
                }
        } else cache.getGamesCache(week.id)
    }.subscribeOn(Schedulers.io())

}

/**Вариант приоритетного получения данных по играм недели из кэша*/
//    override fun getGames(season: Season, week: Week): Single<List<Game>> =
//        networkStatus.isOnlineSingle().flatMap { isOnline ->
//            if (isOnline) {
//                cache.getGamesCache(week.id)
//                    .flatMap {
//                        if (it.isEmpty()) {
//                            api.getWeeklySchedule(
//                                season.year.toString(),
//                                season.type.code,
//                                week.sequence.toString()
//                            )
//                                .flatMap { schedule ->
//                                    cache.putGamesCache(schedule)
//                                        .toSingle { schedule.week.games }
//                                }
//                        } else Single.just(it)
//                    }
//            } else cache.getGamesCache(week.id)
//        }
//        .subscribeOn(Schedulers.io())