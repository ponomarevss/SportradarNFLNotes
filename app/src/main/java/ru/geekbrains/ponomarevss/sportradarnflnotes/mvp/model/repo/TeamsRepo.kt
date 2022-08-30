package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.api.IDataSource
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.ITeamsCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Team
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.mapReToTeams
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.network.INetworkStatus
import java.lang.Thread.sleep

class TeamsRepo(
    private val ioScheduler: Scheduler,
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: ITeamsCache
) : ITeamsRepo {

    companion object {
        private const val REQUESTS_GAP = 1100L
    }

    override fun getTeam(teamId: String): Single<Team> = cache.getTeam(teamId).doOnError { println(it.message) }.subscribeOn(ioScheduler)

    override fun getTeams(): Single<List<Team>> = cache.getTeams().flatMap { teamsList ->
        if (teamsList.isEmpty()) {
            networkStatus.isOnlineSingle().flatMap { isOnline ->
                if (isOnline) {
                    sleep(REQUESTS_GAP)
                    api.getLeagueHierarchy().flatMap { reHierarchy ->
                        val teams = mapReToTeams(reHierarchy)
                        cache.putTeams(teams).toSingle { teams }
                    }
                } else throw Throwable("Unable to fetch teams")
            }
        } else {
            Single.just(teamsList)
        }
    }
}