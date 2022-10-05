package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.cache

import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Game
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Team

interface IGamesCache {

    suspend fun putGames(games: List<Game>, weekId: String)
    suspend fun getGames(weekId: String, teams: List<Team>): List<Game>

    suspend fun putGame(game: Game, weekId: String)
    suspend fun getGame(gameId: String, teams: List<Team>): Game?
}