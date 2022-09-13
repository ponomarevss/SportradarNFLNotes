package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.room

import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IWeeksCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.mapRoomToWeek
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.mapWeekToRoom
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.db.SportradarDatabase

class RoomWeeksCache(private val db: SportradarDatabase) : IWeeksCache {

    override suspend fun putWeeks(weeks: List<Week>, season: Season) {
        db.weekDao.insert(weeks.map { mapWeekToRoom(it, season) })
    }

    override suspend fun getWeeks(seasonId: String): List<Week> =
        db.weekDao.findForSeasonId(seasonId).map { mapRoomToWeek(it) }
}