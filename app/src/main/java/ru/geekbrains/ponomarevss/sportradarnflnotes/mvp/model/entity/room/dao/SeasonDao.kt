package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.dao

import androidx.room.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomSeason

@Dao
interface SeasonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomSeasons: List<RoomSeason>)

    @Query("SELECT * FROM RoomSeason")
    fun getAll(): List<RoomSeason>

    @Query("SELECT * FROM RoomSeason WHERE year = :year AND type = :type LIMIT 1")
    fun select(year: Int, type: String): RoomSeason?
}
