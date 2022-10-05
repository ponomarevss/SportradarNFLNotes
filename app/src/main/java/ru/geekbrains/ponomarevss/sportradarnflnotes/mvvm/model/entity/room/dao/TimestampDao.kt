package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.room.RoomTimestamp

@Dao
interface TimestampDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(timestamp: RoomTimestamp)

    @Query("SELECT * FROM RoomTimestamp WHERE name = :name LIMIT 1")
    fun findForName(name: String): RoomTimestamp?
}