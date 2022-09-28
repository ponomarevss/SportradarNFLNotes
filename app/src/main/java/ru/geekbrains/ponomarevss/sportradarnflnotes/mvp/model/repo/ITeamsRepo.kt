package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo

import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Team

interface ITeamsRepo {

    suspend fun getTeam(teamId: String): Team?
    suspend fun getApiTeams()/*: List<Team>*/
    suspend fun getCachedTeams(): List<Team>
}