package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.room.dao

import androidx.room.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.room.RoomWeek

@Dao
interface WeekDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(roomWeek: RoomWeek)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(roomWeeks: List<RoomWeek>)

    @Query("SELECT * FROM RoomWeek WHERE seasonId = :seasonId AND sequence = :sequence LIMIT 1")
    suspend fun findOneBySeasonId(seasonId: String, sequence: Int): RoomWeek?

    @Query("SELECT * FROM RoomWeek WHERE seasonId = :seasonId")
    suspend fun findForSeasonId(seasonId: String): List<RoomWeek>
}