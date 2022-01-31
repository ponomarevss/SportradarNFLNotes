package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.room

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IWeeksCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.db.Database

class RoomWeeksCache(val db: Database) : IWeeksCache {
    override fun putWeeks(weeks: List<Week>, seasonId: String): Completable = Completable.fromAction {
        weeks.map {
            RoomWeek(it.id, it.sequence, it.title, seasonId)
        }.let {
            db.weekDao.insert(it)
        }
    }

    override fun getWeeks(seasonId: String): Single<List<Week>> = Single.fromCallable {
        db.weekDao.findForSeasonId(seasonId).map {
            Week(it.id, it.sequence, it.title)
        }
    }

}