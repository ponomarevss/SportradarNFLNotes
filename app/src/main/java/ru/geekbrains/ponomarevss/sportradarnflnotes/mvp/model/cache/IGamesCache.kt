package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache

import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Game
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Week

interface IGamesCache {

    suspend fun putGames(games: List<Game>, week: Week)
    suspend fun getGames(week: Week): List<Game>

    suspend fun putGame(game: Game, week: Week)
    suspend fun getGame(gameId: String): Game?
}