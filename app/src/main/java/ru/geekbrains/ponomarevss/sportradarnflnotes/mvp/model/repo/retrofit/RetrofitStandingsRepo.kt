package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.retrofit

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.api.IDataSource
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IStandingsCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.ITeamsCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Standings
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.mapReToTeams
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.IStandingsRepo
import java.lang.Thread.sleep

class RetrofitStandingsRepo(
    private val ioScheduler: Scheduler,
    private val api: IDataSource,
    private val cache: IStandingsCache,
    private val teamsCache: ITeamsCache
) : IStandingsRepo {

    companion object {
        private const val REQUESTS_GAP = 1100L
    }

    override fun getStandingsList(seasonId: String): Single<List<Standings>> = cache.getStandingsList(seasonId).subscribeOn(ioScheduler)
    //что если standingsList пустой?
    //тогда надо создать standingsList с нулями для всех команд
    //для этого надо получить из кеша список команд
    //что если в кеше нет команд?
    //тогда надо взять список команд из api, создать standingsList

    override fun getStandings(seasonId: String, teamId: String): Single<Standings> = cache.getStandings(seasonId, teamId).subscribeOn(ioScheduler)

    override fun putStandings(standings: Standings): Completable = cache.putStandings(standings).subscribeOn(ioScheduler)

    override fun putStandingsList(standingsList: List<Standings>): Completable = cache.putStandingsList(standingsList).subscribeOn(ioScheduler)
}