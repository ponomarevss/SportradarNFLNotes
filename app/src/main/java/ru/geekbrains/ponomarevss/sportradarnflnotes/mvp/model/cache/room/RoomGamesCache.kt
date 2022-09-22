package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.room

import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IGamesCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Game
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.mapGameToRoom
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.mapRoomToGame
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.db.SportradarDatabase

class RoomGamesCache(private val db: SportradarDatabase) : IGamesCache {

    override suspend fun putGames(games: List<Game>, week: Week) {
        db.gameDao.insert(games.map { mapGameToRoom(it, week) })
    }

    override suspend fun getGames(week: Week): List<Game> =
        db.gameDao.findForWeekId(week.id).map { mapRoomToGame(it) }

    override suspend fun putGame(game: Game, week: Week) {
        db.gameDao.insert(mapGameToRoom(game, week))
    }

    override suspend fun getGame(gameId: String): Game? =
        db.gameDao.findForId(gameId)?.let { mapRoomToGame(it) }
}