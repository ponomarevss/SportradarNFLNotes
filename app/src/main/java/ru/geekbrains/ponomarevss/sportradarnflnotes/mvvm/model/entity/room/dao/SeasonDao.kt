package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.room.dao

import androidx.room.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.room.RoomSeason

@Dao
interface SeasonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(roomSeasons: List<RoomSeason>)

    @Query("SELECT * FROM RoomSeason")
    suspend fun getAll(): List<RoomSeason>

    @Query("SELECT * FROM RoomSeason WHERE year = :year AND type = :type LIMIT 1")
    suspend fun select(year: Int, type: String): RoomSeason?
}
