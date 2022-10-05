package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.cache

import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Team

interface ITeamsCache {

    suspend fun putTeams(teams: List<Team>)
    suspend fun getTeams(): List<Team>

    suspend fun getTeam(teamId: String): Team?
}