package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.room.RoomTeam

@Dao
interface TeamDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(roomTeam: RoomTeam)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(roomTeams: List<RoomTeam>)

    @Query("SELECT * FROM RoomTeam")
    suspend fun getAll(): List<RoomTeam>

    @Query("SELECT * FROM RoomTeam WHERE id = :id LIMIT 1")
    suspend fun findById(id: String): RoomTeam?

//    @Query("SELECT * FROM RoomTeam WHERE division = :division")
//    suspend fun findByDivisionId(division: String): List<RoomTeam>
}
