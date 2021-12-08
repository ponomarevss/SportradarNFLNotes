package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.dao

import androidx.room.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomGame
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomWeek

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomGame: RoomGame)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg roomGames: RoomGame)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomGames: List<RoomGame>)

    @Update
    fun update(roomGame: RoomGame)

    @Update
    fun update(vararg roomGames: RoomGame)

    @Update
    fun update(roomGames: List<RoomGame>)

    @Delete
    fun delete(roomGame: RoomGame)

    @Delete
    fun delete(vararg roomGames: RoomGame)

    @Delete
    fun delete(roomGames: List<RoomGame>)

    @Query("SELECT * FROM RoomGame")
    fun getAll(): List<RoomGame>

    @Query("SELECT * FROM RoomGame WHERE id = :id LIMIT 1")
    fun findById(id: String): RoomGame?

    @Query("SELECT * FROM RoomGame WHERE weekId = :weekId")
    fun findByWeek(weekId: String): List<RoomGame>

    @Query("SELECT * FROM RoomGame WHERE homeId = :homeId OR awayId = :awayId")
    fun findByRival(homeId: String, awayId: String): List<RoomGame>
}