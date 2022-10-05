package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.cache.room

import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.cache.IWeeksCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.mapRoomToWeek
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.mapWeekToRoom
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.room.db.SportradarDatabase

class RoomWeeksCache(private val db: SportradarDatabase) : IWeeksCache {

    override suspend fun putWeeks(weeks: List<Week>, seasonId: String) {
            db.weekDao.insert(weeks.map { mapWeekToRoom(it, seasonId) })
    }

    override suspend fun getWeeks(seasonId: String): List<Week> =
            db.weekDao.findForSeasonId(seasonId).map { mapRoomToWeek(it) }
}