package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomTeam

@Dao
interface TeamDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomTeam: RoomTeam)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomTeams: List<RoomTeam>)

    @Query("SELECT * FROM RoomTeam")
    fun getAll(): List<RoomTeam>

    @Query("SELECT * FROM RoomTeam WHERE id = :id LIMIT 1")
    fun findById(id: String): RoomTeam?

//    @Query("SELECT * FROM RoomTeam WHERE division = :division")
//    fun findByDivisionId(division: String): List<RoomTeam>
}
