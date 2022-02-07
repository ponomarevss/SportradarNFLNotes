package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.retrofit

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.api.IDataSource
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IConferencesCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IStandingsCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Standings
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.IStandingsRepo

class RetrofitStandingsRepo(val api: IDataSource, val cache: IStandingsCache, val teamsCache: IConferencesCache) : IStandingsRepo {
    override fun getStandings(seasonId: String, teamId: String): Single<Standings> = cache.getStandings(seasonId, teamId)
        .subscribeOn(Schedulers.io())

    override fun getStandingsList(seasonId: String): Single<List<Standings>> = cache.getStandingsList(seasonId).flatMap { standingsList ->
        if (standingsList.isEmpty()) {
            // в этой ветке описываем что делать если standingsList пустой
            // надо вернуть Single<List<Standings>>
            teamsCache.getTeams().map { teams ->
                // проверяем, не пустой ли teams
                if (teams.isEmpty()) {
                    //здесь надо получить из api иерархию и положить в бд конференции
                    api.getLeagueHierarchy().flatMap { hierarchy ->
                        teamsCache.putConferences(hierarchy.conferences).toSingleDefault(hierarchy)
                    }
                    //получаем из бд новые команды, создаем из них List<Standings>
                    teamsCache.getTeams().blockingGet().map { newTeam ->
                        Standings(seasonId, newTeam.id)
                    }
                } else {
                    //если список команд не пустой, трансформируем его в List<Standings>
                    teams.map { team ->
                        Standings(seasonId, team.id)
                    }
                }
            }
        } else {
            // если standingsList не пустой
            Single.just(standingsList)
        }
    }.subscribeOn(Schedulers.io())

    override fun putStandings(standings: Standings): Completable = Completable.fromAction {
        cache.putStandings(standings)
    }

}