package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.dao

import androidx.room.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomLeague
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomStandings

@Dao
interface StandingsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomStandings: RoomStandings)

    @Update
    fun update(roomStandings: RoomStandings)

    @Delete
    fun delete(roomStandings: RoomStandings)

    @Query("SELECT * FROM RoomStandings WHERE seasonId = :seasonId AND teamId = :teamId LIMIT 1")
    fun select(seasonId: String, teamId: String): RoomStandings?
}
