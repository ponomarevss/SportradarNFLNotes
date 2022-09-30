package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache

import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Standings
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Team

interface IStandingsCache {

    suspend fun putStandings(standings: Standings)
    suspend fun getStandings(seasonId: String, teamId: String, teams: List<Team>): Standings?

    suspend fun putStandingsList(standingsList: List<Standings>)
    suspend fun getStandingsList(seasonId: String, teams: List<Team>): List<Standings>
}