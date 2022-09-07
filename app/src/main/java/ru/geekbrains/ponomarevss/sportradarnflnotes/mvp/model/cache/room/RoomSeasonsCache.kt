package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.room

import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.ISeasonsCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.mapRoomToSeason
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.mapSeasonToRoom
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.db.SportradarDatabase

class RoomSeasonsCache(private val db: SportradarDatabase) : ISeasonsCache {

    override suspend fun putSeasons(seasons: List<Season>) {
        db.seasonDao.insert(seasons.map { mapSeasonToRoom(it) })
    }

    override suspend fun getSeasons(): List<Season> =
        db.seasonDao.getAll().map { mapRoomToSeason(it) }
}