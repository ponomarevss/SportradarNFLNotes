package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo

import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Game
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Team
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Week

interface IGamesRepo {

    suspend fun getCachedGames(weekId: String, teams: List<Team>): List<Game>
    suspend fun getApiGames(season: Season, week: Week, teams: List<Team>): List<Game>

    suspend fun putGame(game: Game, weekId: String)
}