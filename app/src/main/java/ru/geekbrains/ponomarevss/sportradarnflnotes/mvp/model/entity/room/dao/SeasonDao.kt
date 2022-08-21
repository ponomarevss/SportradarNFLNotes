package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.dao

import androidx.room.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.old.RoomSeason

@Dao
interface SeasonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomSeason: RoomSeason)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg roomSeasons: RoomSeason)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomSeasons: List<RoomSeason>)

    @Update
    fun update(roomSeason: RoomSeason)

    @Update
    fun update(vararg roomSeasons: RoomSeason)

    @Update
    fun update(roomSeasons: List<RoomSeason>)

    @Delete
    fun delete(roomSeason: RoomSeason)

    @Delete
    fun delete(vararg roomSeasons: RoomSeason)

    @Delete
    fun delete(roomSeasons: List<RoomSeason>)

    @Query("SELECT * FROM RoomSeason")
    fun getAll(): List<RoomSeason>

    @Query("SELECT * FROM RoomSeason WHERE year = :year AND type = :type LIMIT 1")
    fun select(year: Int, type: String): RoomSeason?
}
