package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.retrofit

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.api.IDataSource
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IConferencesCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Conference
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Team
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.network.INetworkStatus
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.IConferencesRepo

class RetrofitConferencesRepo(val api: IDataSource, private val networkStatus: INetworkStatus, private val cache: IConferencesCache) :
    IConferencesRepo {

    override fun getConferences(): Single<List<Conference>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            cache.getConferences()
                .flatMap {
                    if (it.isEmpty()) {
                        api.getLeagueHierarchy()
                            .flatMap { hierarchy ->
                                cache.putConferences(hierarchy.conferences)
                                    .toSingle { cache.getConferences().blockingGet() }
//                                    .toSingle { hierarchy.conferences }
                            }
                    } else Single.just(it)
                }
        } else cache.getConferences()
    }.subscribeOn(Schedulers.io())

    override fun getTeam(teamId: String): Single<Team> = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            cache.getTeam(teamId).onErrorResumeNext {
                api.getLeagueHierarchy().flatMap {
                    cache.putConferences(it.conferences).toSingle { cache.getTeam(teamId).blockingGet() }
                }
            }
        } else cache.getTeam(teamId)
    }.subscribeOn(Schedulers.io())

}