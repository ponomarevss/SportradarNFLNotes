package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.cache

import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.room.RoomTimestamp

interface ITimestampCache {

    suspend fun putTimestamp(timestamp: RoomTimestamp)
    suspend fun getTimestamp(name: String): RoomTimestamp?
    suspend fun updateTimestamp(name: String)
    suspend fun isUpdated(name: String, period: Long): Boolean
}