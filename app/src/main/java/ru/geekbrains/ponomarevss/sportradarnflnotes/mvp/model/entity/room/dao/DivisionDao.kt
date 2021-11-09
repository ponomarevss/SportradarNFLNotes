package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.dao

import androidx.room.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomConference
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomDivision

@Dao
interface DivisionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomDivision: RoomDivision)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg roomDivisions: RoomDivision)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomDivisions: List<RoomDivision>)

    @Update
    fun update(roomDivision: RoomDivision)

    @Update
    fun update(vararg roomDivisions: RoomDivision)

    @Update
    fun update(roomDivisions: List<RoomDivision>)

    @Delete
    fun delete(roomDivision: RoomDivision)

    @Delete
    fun delete(vararg roomDivisions: RoomDivision)

    @Delete
    fun delete(roomDivisions: List<RoomDivision>)

    @Query("SELECT * FROM RoomDivision")
    fun getAll(): List<RoomDivision>

    @Query("SELECT * FROM RoomDivision WHERE id = :id LIMIT 1")
    fun findById(id: String): RoomDivision?
}
