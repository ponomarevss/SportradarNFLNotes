package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo

import kotlinx.coroutines.delay
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.REQUESTS_GAP
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.api.IDataSource
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.ITeamsCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Team
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.mapReToTeams

class TeamsRepo(private val api: IDataSource, private val cache: ITeamsCache) : ITeamsRepo {

    override suspend fun getTeam(teamId: String): Team =
        cache.getTeam(teamId) ?: throw Throwable("No team found")

    override suspend fun getApiTeams()/*: List<Team>*/ {
        delay(REQUESTS_GAP)
        cache.putTeams(mapReToTeams(api.getLeagueHierarchy()))
//        val teams: List<Team> = mapReToTeams(api.getLeagueHierarchy())
//        cache.putTeams(teams)
//        return teams
    }

    override suspend fun getCachedTeams(): List<Team> = cache.getTeams()
}