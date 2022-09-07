package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.room

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IGamesCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Game
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.mapGameToRoom
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.mapRoomToGame
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.db.SportradarDatabase

class RoomGamesCache(private val db: SportradarDatabase) : IGamesCache {

//    override fun putGames(games: List<Game>, week: Week): Completable = Completable.fromAction {
//        games.map { db.gameDao.insert(mapGameToRoom(it, week)) }
//    }

//    override fun getGames(week: Week): Single<List<Game>> = Single.fromCallable {
//        db.gameDao.findForWeekId(week.id).map { mapRoomToGame(it) }
//    }

//    override fun putGame(game: Game, week: Week): Completable = Completable.fromAction {
//        db.gameDao.insert(mapGameToRoom(game, week))
//    }

    //    override fun getGame(gameId: String): Single<Game> = Single.fromCallable {
//        db.gameDao.findForId(gameId)?.let { mapRoomToGame(it) }
//    }
    override fun putGames(games: List<Game>, week: Week): Completable {
        TODO("Not yet implemented")
    }

    override fun getGames(week: Week): Single<List<Game>> {
        TODO("Not yet implemented")
    }

    override fun putGame(game: Game, week: Week): Completable {
        TODO("Not yet implemented")
    }

    override fun getGame(gameId: String): Single<Game> {
        TODO("Not yet implemented")
    }
}