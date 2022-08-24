package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.dao

import androidx.room.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomStandings

@Dao
interface StandingsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomStandings: RoomStandings)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomStandings: List<RoomStandings>)

    @Query("SELECT * FROM RoomStandings WHERE seasonId = :seasonId AND teamId = :teamId LIMIT 1")
    fun select(seasonId: String, teamId: String): RoomStandings?

    @Query("SELECT * FROM RoomStandings WHERE seasonId = :seasonId")
    fun selectForSeason(seasonId: String): List<RoomStandings>
}
