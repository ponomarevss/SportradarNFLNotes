package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.dao

import androidx.room.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomLeague

@Dao
interface LeagueDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomLeague: RoomLeague)

    @Update
    fun update(roomLeague: RoomLeague)

    @Delete
    fun delete(roomLeague: RoomLeague)

//    @Query("SELECT * FROM RoomLeague WHERE id = :id LIMIT 1")
//    fun findById(id: String): RoomLeague?

    @Query("SELECT * FROM RoomLeague WHERE name = '3c6d318a-6164-4290-9bbc-bf9bb21cc4b8' LIMIT 1")
    fun select(): RoomLeague
}
