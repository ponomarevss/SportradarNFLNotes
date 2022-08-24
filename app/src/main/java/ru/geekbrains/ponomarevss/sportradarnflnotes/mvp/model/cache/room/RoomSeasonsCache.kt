package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.room

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.ISeasonsCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.mapRoomToSeason
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.mapSeasonToRoom
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.db.Database

class RoomSeasonsCache(private val db: Database) : ISeasonsCache {

    override fun putSeasons(seasons: List<Season>): Completable = Completable.fromAction {
        db.seasonDao.insert(seasons.map { mapSeasonToRoom(it) })
    }

    override fun getSeasons(): Single<List<Season>> = Single.fromCallable {
        db.seasonDao.getAll().map { mapRoomToSeason(it) }
    }
}