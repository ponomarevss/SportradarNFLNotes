package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.cache.room

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.cache.IGamesCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Game
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Team
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.mapGameToRoom
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.mapRoomToGame
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.room.db.SportradarDatabase

class RoomGamesCache(private val db: SportradarDatabase) : IGamesCache {

    override suspend fun putGames(games: List<Game>, weekId: String) {
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            db.gameDao.insert(games.map { mapGameToRoom(it, weekId) })
        }
    }

    override suspend fun getGames(weekId: String, teams: List<Team>): List<Game> =
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            db.gameDao.findForWeekId(weekId).map { mapRoomToGame(it, teams) }
        }

    override suspend fun putGame(game: Game, weekId: String) {
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            db.gameDao.insert(mapGameToRoom(game, weekId))
        }
    }

    override suspend fun getGame(gameId: String, teams: List<Team>): Game? =
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            db.gameDao.findForId(gameId)?.let { mapRoomToGame(it, teams) }
        }
}