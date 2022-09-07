package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.room

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IWeeksCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.mapRoomToWeek
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.mapWeekToRoom
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.db.SportradarDatabase

class RoomWeeksCache(private val db: SportradarDatabase) : IWeeksCache {

    //    override fun putWeeks(weeks: List<Week>, season: Season): Completable = Completable.fromAction {
//        db.weekDao.insert(weeks.map { mapWeekToRoom(it, season) })
//    }
//
//    override fun getWeeks(seasonId: String): Single<List<Week>> = Single.fromCallable {
//        db.weekDao.findForSeasonId(seasonId).map { mapRoomToWeek(it) }
//    }
    override fun putWeeks(weeks: List<Week>, season: Season): Completable {
        TODO("Not yet implemented")
    }

    override fun getWeeks(seasonId: String): Single<List<Week>> {
        TODO("Not yet implemented")
    }
}