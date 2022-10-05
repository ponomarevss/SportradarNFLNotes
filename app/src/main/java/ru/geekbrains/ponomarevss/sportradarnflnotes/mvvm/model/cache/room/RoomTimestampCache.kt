package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.cache.room

import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.cache.ITimestampCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.room.RoomTimestamp
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.room.db.SportradarDatabase

class RoomTimestampCache(private val db: SportradarDatabase) : ITimestampCache {

    override suspend fun putTimestamp(timestamp: RoomTimestamp) {
        db.timestampDao.insert(timestamp)
    }

    override suspend fun getTimestamp(name: String): RoomTimestamp? =
        db.timestampDao.findForName(name)


    override suspend fun updateTimestamp(name: String) {
        putTimestamp(RoomTimestamp(name, System.currentTimeMillis()))
    }

    override suspend fun isUpdated(name: String, period: Long): Boolean {
        val lastUpdate = getTimestamp(name)?.timestamp ?: 0
        return System.currentTimeMillis() - lastUpdate < period
    }
}