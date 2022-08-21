package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.room

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IGamesCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Game
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Period
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Rival
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Scoring
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.db.Database
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.old.RoomGame
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.old.RoomPeriod
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.old.RoomRival
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.old.RoomScoring

class RoomGamesCache(val db: Database) : IGamesCache {

    companion object{
        private const val HOME = "home"
        private const val AWAY = "away"
    }

    override fun putGames(games: List<Game>, weekId: String): Completable = Completable.fromAction {
        games.map {
            db.gameDao.insert(RoomGame(it.id, it.status, it.scheduled, it.home.id, it.away.id, weekId, it.isWatched))
            putHome(it)
            putAway(it)
            putScoring(it)
        }
    }

    override fun getGames(weekId: String): Single<List<Game>> = Single.fromCallable {
        db.gameDao.findForWeekId(weekId).map {
            Game(it.id, it.status, it.scheduled, getHome(it.id), getAway(it.id), getScoring(it.id), it.isWatched)
        }
    }


    override fun putGame(game: Game, weekId: String): Completable = Completable.fromAction {
        with(game) {
            db.gameDao.insert(RoomGame(id, status, scheduled, home.id, away.id, weekId, isWatched))
            putHome(this)
            putAway(this)
            putScoring(this)
        }
    }

    override fun getGame(gameId: String): Single<Game> = Single.fromCallable {
        db.gameDao.findForId(gameId)?.let {
            Game(it.id, it.status, it.scheduled, getHome(it.id), getAway(it.id), getScoring(it.id), it.isWatched)
        }
    }

    private fun getHome(gameId: String) = db.rivalDao.findHomeByGameId(gameId).run {
        Rival(id, name, alias, gameNumber)
    }

    private fun getAway(gameId: String) = db.rivalDao.findAwayByGameId(gameId).run {
        Rival(id, name, alias, gameNumber)
    }

    private fun getScoring(gameId: String) = db.scoringDao.select(gameId)?.run {
        Scoring(homePoints, awayPoints, getPeriods(id))
    }

    private fun getPeriods(scoringId: String) = db.periodDao.select(scoringId).map {
        Period(it.periodType, it.id, it.number, it.homePoints, it.awayPoints)
    }


    private fun putHome(game: Game) = with(game.home) {
        if (alias == "JAC") {
            alias = "JAX"
        }
        RoomRival(id, HOME, name, alias, gameNumber, game.id).let { db.rivalDao.insert(it) }
    }

    private fun putAway(game: Game) = with(game.away) {
        if (alias == "JAC") {
            alias = "JAX"
        }
        RoomRival(id, AWAY, name, alias, gameNumber, game.id).let { db.rivalDao.insert(it) }
    }

    private fun putScoring(game: Game) = game.scoring?.run {
        db.scoringDao.insert(RoomScoring(game.id, homePoints, awayPoints, game.id))
        putPeriods(game.id, this)
    }

    private fun putPeriods(gameId: String, scoring: Scoring) = scoring.periods.map {
        RoomPeriod(it.id, it.periodType, it.number, it.homePoints, it.awayPoints, gameId)
    }.let { db.periodDao.insert(it) }
}