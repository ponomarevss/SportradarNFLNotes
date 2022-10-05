package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.cache

import kotlinx.coroutines.flow.Flow
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Standings
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Team

interface IStandingsCache {

    suspend fun putStandings(standings: Standings)
    suspend fun getStandings(seasonId: String, teamId: String, teams: List<Team>): Standings?

    suspend fun putStandingsList(standingsList: List<Standings>)
    suspend fun getStandingsList(seasonId: String, teams: List<Team>): List<Standings>

    fun observeStandingsList(seasonId: String, teams: List<Team>): Flow<List<Standings>>
}