package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.api.IDataSource
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IConferencesCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IWeekCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Conference
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.network.INetworkStatus

class RetrofitConferencesRepo(
    val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: IConferencesCache
) : IConferencesRepo {

    override fun getConferences(): Single<List<Conference>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                cache.getConferences()
                    .flatMap {
                        if (it.isEmpty()) {
                            api.getLeagueHierarchy()
                                .flatMap { hierarchy ->
                                    cache.putConferences(hierarchy.conferences)
                                        .toSingle { hierarchy.conferences }
                                }
                        } else Single.just(it)
                    }
            } else cache.getConferences()
        }
        .subscribeOn(Schedulers.io())
}