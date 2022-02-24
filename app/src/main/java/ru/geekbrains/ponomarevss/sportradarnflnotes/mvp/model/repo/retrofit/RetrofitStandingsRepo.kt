package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.retrofit

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.api.IDataSource
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IConferencesCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IStandingsCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Standings
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.IStandingsRepo
import java.lang.Thread.sleep

class RetrofitStandingsRepo(val api: IDataSource, val cache: IStandingsCache, val teamsCache: IConferencesCache) : IStandingsRepo {

    companion object{
        private const val REQUESTS_GAP = 1100L
    }

    override fun getStandingsList(seasonId: String): Single<List<Standings>> = cache.getStandingsList(seasonId).flatMap { standingsList ->
        if (standingsList.isEmpty()) {
            teamsCache.getTeams().flatMap { teams ->
                if (teams.isEmpty()) {
                    sleep(REQUESTS_GAP)
                    api.getLeagueHierarchy().flatMap { hierarchy ->
                        teamsCache.putConferences(hierarchy.conferences).toSingle {
                            teamsCache.getTeams().blockingGet().map { team ->
                                Standings(seasonId, team.id).apply { cache.putStandings(this).subscribe() }
                            }.also { println("1 get teams from api, put em to a cache and create standings list") }
                        }
                    }
                } else {
                    Single.fromCallable {
                        teams.map { team ->
                            Standings(seasonId, team.id).apply { cache.putStandings(this).subscribe() }
                        }.also { println("2 get teams from cache and create standings list") } }
                }
            }
        } else {
            Single.just(standingsList).also { println("3 get standings list from cache") }
        }
    }.subscribeOn(Schedulers.io())

    override fun getStandings(seasonId: String, teamId: String): Single<Standings> = cache.getStandings(seasonId, teamId).subscribeOn(Schedulers.io())

    override fun putStandings(standings: Standings): Completable = cache.putStandings(standings).subscribeOn(Schedulers.io())

    override fun putStandingsList(standingsList: List<Standings>): Completable = cache.putStandingsList(standingsList).subscribeOn(Schedulers.io())
}