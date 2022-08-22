package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.dao

import androidx.room.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomScoring

@Dao
interface ScoringDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomScoring: RoomScoring)

    @Query("SELECT * FROM RoomScoring WHERE gameId = :gameId LIMIT 1")
    fun select(gameId: String): RoomScoring?
}
