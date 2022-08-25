package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.retrofit

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IStandingsCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Standings
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Team
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.IStandingsRepo

class RetrofitStandingsRepo(
    private val ioScheduler: Scheduler,
    private val cache: IStandingsCache,
    private val teams: List<Team>
) : IStandingsRepo {

    override fun getStandingsList(seasonId: String): Single<List<Standings>> = cache.getStandingsList(seasonId).flatMap { standingsList ->
        if (standingsList.isEmpty()) {
            val newStandingsList = teams.map { Standings(seasonId, it.id) }
            cache.putStandingsList(newStandingsList).toSingle { newStandingsList }
        } else Single.just(standingsList)
    }.subscribeOn(ioScheduler)

    override fun getStandings(seasonId: String, teamId: String): Single<Standings> = cache.getStandings(seasonId, teamId).subscribeOn(ioScheduler)

    override fun putStandings(standings: Standings): Completable = cache.putStandings(standings).subscribeOn(ioScheduler)

    override fun putStandingsList(standingsList: List<Standings>): Completable = cache.putStandingsList(standingsList).subscribeOn(ioScheduler)
}