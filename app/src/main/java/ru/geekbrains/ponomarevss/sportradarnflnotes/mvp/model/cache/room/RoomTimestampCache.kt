package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.room

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.HOURLY_UPDATE
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.ITimestampCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomTimestamp
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.db.SportradarDatabase

class RoomTimestampCache(private val db: SportradarDatabase) : ITimestampCache {

    override suspend fun putTimestamp(timestamp: RoomTimestamp) {
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            db.timestampDao.insert(timestamp)
        }
    }

    override suspend fun getTimestamp(name: String): RoomTimestamp? =
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            db.timestampDao.findForName(name)
        }


    override suspend fun updateTimestamp(name: String) {
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            putTimestamp(RoomTimestamp(name, System.currentTimeMillis()))
        }
    }

    override suspend fun isUpdated(name: String, period: Long): Boolean {
        val lastUpdate = withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            getTimestamp(name)?.timestamp ?: 0
        }
        return System.currentTimeMillis() - lastUpdate < period
    }

}