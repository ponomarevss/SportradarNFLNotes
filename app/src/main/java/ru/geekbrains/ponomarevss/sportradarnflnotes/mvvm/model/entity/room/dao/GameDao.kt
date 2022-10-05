package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.room.RoomGame

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(roomGame: RoomGame)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(roomGames: List<RoomGame>)

    @Query("SELECT * FROM RoomGame WHERE id = :id LIMIT 1")
    suspend fun findForId(id: String): RoomGame?

    @Query("SELECT * FROM RoomGame WHERE weekId = :weekId")
    suspend fun findForWeekId(weekId: String): List<RoomGame>

    @Query("SELECT * FROM RoomGame WHERE homeId = :rivalId OR awayId = :rivalId")
    suspend fun findForRivalId(rivalId: String): List<RoomGame>
}