package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.repo

import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Team

interface ITeamsRepo {

    suspend fun getTeam(teamId: String): Team?
    suspend fun getApiTeams()/*: List<Team>*/
    suspend fun getCachedTeams(): List<Team>
}