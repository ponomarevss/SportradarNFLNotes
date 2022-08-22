package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomGame

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomGame: RoomGame)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomGames: List<RoomGame>)

    @Query("SELECT * FROM RoomGame WHERE id = :id LIMIT 1")
    fun findForId(id: String): RoomGame?

    @Query("SELECT * FROM RoomGame WHERE weekId = :weekId")
    fun findForWeekId(weekId: String): List<RoomGame>

    @Query("SELECT * FROM RoomGame WHERE homeId = :rivalId OR awayId = :rivalId")
    fun findForRivalId(rivalId: String): List<RoomGame>
}