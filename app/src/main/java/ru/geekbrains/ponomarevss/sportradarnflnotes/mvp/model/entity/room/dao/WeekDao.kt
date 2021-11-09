package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.dao

import androidx.room.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomWeek

@Dao
interface WeekDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomWeek: RoomWeek)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg roomWeeks: RoomWeek)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomWeeks: List<RoomWeek>)

    @Update
    fun update(roomWeek: RoomWeek)

    @Update
    fun update(vararg roomWeeks: RoomWeek)

    @Update
    fun update(roomWeeks: List<RoomWeek>)

    @Delete
    fun delete(roomWeek: RoomWeek)

    @Delete
    fun delete(vararg roomWeeks: RoomWeek)

    @Delete
    fun delete(roomWeeks: List<RoomWeek>)

    @Query("SELECT * FROM RoomWeek")
    fun getAll(): List<RoomWeek>

    @Query("SELECT * FROM RoomWeek WHERE id = :id LIMIT 1")
    fun findById(id: String): RoomWeek?
}