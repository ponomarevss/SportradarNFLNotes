package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.room.dao

import androidx.room.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.room.RoomWeek

@Dao
interface WeekDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomWeek: RoomWeek)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomWeeks: List<RoomWeek>)

    @Query("SELECT * FROM RoomWeek WHERE seasonId = :seasonId AND sequence = :sequence LIMIT 1")
    fun findOneBySeasonId(seasonId: String, sequence: Int): RoomWeek?

    @Query("SELECT * FROM RoomWeek WHERE seasonId = :seasonId")
    fun findForSeasonId(seasonId: String): List<RoomWeek>
}