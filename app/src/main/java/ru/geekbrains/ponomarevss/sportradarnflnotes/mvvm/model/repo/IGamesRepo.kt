package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.repo

import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Game
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Team

interface IGamesRepo {

    suspend fun getCachedGames(weekId: String, teams: List<Team>): List<Game>
//    suspend fun getApiGames(season: Season, week: Week, teams: List<Team>): List<Game>

    suspend fun putGame(game: Game, weekId: String)
}