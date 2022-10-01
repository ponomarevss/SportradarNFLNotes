package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.room

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IGamesCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Game
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Team
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.mapGameToRoom
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.mapRoomToGame
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.db.SportradarDatabase

class RoomGamesCache(private val db: SportradarDatabase) : IGamesCache {

    override suspend fun putGames(games: List<Game>, week: Week) {
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            db.gameDao.insert(games.map { mapGameToRoom(it, week) })
        }
    }

    override suspend fun getGames(week: Week, teams: List<Team>): List<Game> =
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            db.gameDao.findForWeekId(week.id).map { mapRoomToGame(it, teams) }
        }

    override suspend fun putGame(game: Game, week: Week) {
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            db.gameDao.insert(mapGameToRoom(game, week))
        }
    }

    override suspend fun getGame(gameId: String, teams: List<Team>): Game? =
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            db.gameDao.findForId(gameId)?.let { mapRoomToGame(it, teams) }
        }
}