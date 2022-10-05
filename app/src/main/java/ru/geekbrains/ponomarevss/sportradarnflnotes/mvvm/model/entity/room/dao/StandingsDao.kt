package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.room.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.room.RoomStandings

@Dao
interface StandingsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(roomStandings: RoomStandings)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(roomStandings: List<RoomStandings>)

    @Query("SELECT * FROM RoomStandings WHERE seasonId = :seasonId AND teamId = :teamId LIMIT 1")
    suspend fun select(seasonId: String, teamId: String): RoomStandings?

    @Query("SELECT * FROM RoomStandings WHERE seasonId = :seasonId")
    suspend fun selectForSeason(seasonId: String): List<RoomStandings>

    @Query("SELECT * FROM RoomStandings WHERE seasonId = :seasonId")
    fun observeForSeason(seasonId: String): Flow<List<RoomStandings>>
}
