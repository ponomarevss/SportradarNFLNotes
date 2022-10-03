package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache

import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Game
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Team
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Week

interface IGamesCache {

    suspend fun putGames(games: List<Game>, weekId: String)
    suspend fun getGames(weekId: String, teams: List<Team>): List<Game>

    suspend fun putGame(game: Game, weekId: String)
    suspend fun getGame(gameId: String, teams: List<Team>): Game?
}