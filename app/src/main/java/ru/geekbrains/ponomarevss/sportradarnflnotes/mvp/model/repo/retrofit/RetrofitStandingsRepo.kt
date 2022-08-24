package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.retrofit

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IStandingsCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.ITeamsCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Standings
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.IStandingsRepo

class RetrofitStandingsRepo(
    private val ioScheduler: Scheduler,
    private val cache: IStandingsCache,
    private val teamsCache: ITeamsCache
) : IStandingsRepo {

    override fun getStandingsList(seasonId: String): Single<List<Standings>> = cache.getStandingsList(seasonId).flatMap { standingsList ->
        if (standingsList.isEmpty()) {
            teamsCache.getTeams().flatMap { cachedTeams ->
                Single.just(cachedTeams.map { Standings(seasonId, it.id) })
            }
        } else Single.just(standingsList)
    }.subscribeOn(ioScheduler)

    override fun getStandings(seasonId: String, teamId: String): Single<Standings> = cache.getStandings(seasonId, teamId).subscribeOn(ioScheduler)

    override fun putStandings(standings: Standings): Completable = cache.putStandings(standings).subscribeOn(ioScheduler)

    override fun putStandingsList(standingsList: List<Standings>): Completable = cache.putStandingsList(standingsList).subscribeOn(ioScheduler)
}