package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.api.IDataSource
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IWeekCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.LeagueHierarchy
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.network.INetworkStatus

class RetrofitWeekRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val cache: IWeekCache
) : IWeekRepo {
    override fun getWeek(year: Int, type: String, sequence: Int): Single<Week> {
        TODO("Not yet implemented")
    }

    override fun getWeeks(year: Int, type: String): Single<List<Week>> {
        TODO("Not yet implemented")
    }

    fun getLH(): Single<LeagueHierarchy> = api.getLeagueHierarchy().subscribeOn(Schedulers.io())
}
