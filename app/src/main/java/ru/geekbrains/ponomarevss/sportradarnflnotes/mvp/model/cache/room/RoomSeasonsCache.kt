package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.room

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.ISeasonsCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.oldcommon.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.oldcommon.Type
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomSeason
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.db.Database

class RoomSeasonsCache(val db: Database) : ISeasonsCache {

    override fun putSeasons(seasons: List<Season>): Completable = Completable.fromAction{
        db.seasonDao.insert(seasons.map {
            RoomSeason(it.id, it.year, it.startDate, it.endDate, it.status, it.type.code)
        })
    }

    override fun getSeasons(): Single<List<Season>> = Single.fromCallable {
        db.seasonDao.getAll().map {
            Season(it.id, it.year, it.startDate, it.endDate, it.status, Type(it.type))
        }
    }
}