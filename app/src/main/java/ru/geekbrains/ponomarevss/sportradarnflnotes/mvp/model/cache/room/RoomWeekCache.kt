package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.room

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IWeekCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.db.Database

class RoomWeekCache(val db: Database) : IWeekCache {

    companion object{
        private const val HOME = "home"
        private const val AWAY = "away"
    }

    override fun getWeek(year: Int, type: String, sequence: Int): Single<Week> =
        Single.fromCallable {
            val roomSeason = db.seasonDao.select(year, type)
                ?: throw RuntimeException("No such season in cache")
            db.weekDao.findOneBySeasonId(roomSeason.id, sequence)?.let {
                Week(it.id, it.sequence, it.title, getGames(it.id))
            }
        }

    override fun getWeeks(year: Int, type: String): Single<List<Week>> =
        Single.fromCallable {
            val roomSeason = db.seasonDao.select(year, type)
                ?: throw RuntimeException("No such season in cache")
            db.weekDao.findAllBySeasonId(roomSeason.id).map {
                Week(it.id, it.sequence, it.title, getGames(it.id))
            }
        }

    override fun putWeek(weeklySchedule: WeeklySchedule): Completable = Completable.fromAction {
        with(weeklySchedule) {
            db.seasonDao.insert(RoomSeason(id, year, type, name))
            db.weekDao.insert(RoomWeek(week.id, week.sequence, week.title, id))
            putGames(week)
        }
    }

    override fun putWeeks(seasonSchedule: SeasonSchedule): Completable = Completable.fromAction{
        with(seasonSchedule) {
            db.seasonDao.insert(RoomSeason(id, year, type, name))
            weeks.map {
                db.weekDao.insert(RoomWeek(it.id, it.sequence, it.title, id))
                putGames(it)
            }
        }
    }

    private fun getGames(weekId: String) = db.gameDao.findByWeek(weekId).map {
        Game(it.id, it.status, it.scheduled, getHome(it.id), getAway(it.id), getScoring(it.id))
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


    private fun putGames(week: Week) = week.games.map {
        db.gameDao.insert(RoomGame(it.id, it.status, it.scheduled, it.home.id, it.away.id, week.id))
        putHome(it)
        putAway(it)
        putScoring(it)
    }

    private fun putHome(game: Game) = with(game.home) {
        RoomRival(id, HOME, name, alias, gameNumber, game.id).let { db.rivalDao.insert(it) }
    }

    private fun putAway(game: Game) = with(game.away) {
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