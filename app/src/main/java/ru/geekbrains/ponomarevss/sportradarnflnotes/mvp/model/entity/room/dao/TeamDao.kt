package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.dao

import androidx.room.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomConference
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomDivision
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomTeam

@Dao
interface TeamDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomTeam: RoomTeam)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg roomTeams: RoomTeam)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomTeams: List<RoomTeam>)

    @Update
    fun update(roomTeam: RoomTeam)

    @Update
    fun update(vararg roomTeams: RoomTeam)

    @Update
    fun update(roomTeams: List<RoomTeam>)

    @Delete
    fun delete(roomTeam: RoomTeam)

    @Delete
    fun delete(vararg roomTeams: RoomTeam)

    @Delete
    fun delete(roomTeams: List<RoomTeam>)

    @Query("SELECT * FROM RoomTeam")
    fun getAll(): List<RoomTeam>

    @Query("SELECT * FROM RoomTeam WHERE id = :id LIMIT 1")
    fun findById(id: String): RoomTeam?

    @Query("SELECT * FROM RoomTeam WHERE divisionId = :divisionId LIMIT 1")
    fun findByDivisionId(divisionId: String): List<RoomTeam>
}
