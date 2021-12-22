package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.retrofit

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.api.IDataSource
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.ISeasonsCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.network.INetworkStatus
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.ISeasonsRepo

class RetrofitSeasonsRepo(
    val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: ISeasonsCache
) : ISeasonsRepo {

    override fun getSeasons(): Single<List<Season>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                cache.getSeasons()
                    .flatMap {
                        if (it.isEmpty()) {
                            api.getSeasons()
                                .flatMap { leagueSeasons ->
                                    cache.putSeasons(leagueSeasons.seasons)
                                        .toSingle { leagueSeasons.seasons }
                                }
                        } else Single.just(it)
                    }
            } else cache.getSeasons()
        }
            .subscribeOn(Schedulers.io())
}


