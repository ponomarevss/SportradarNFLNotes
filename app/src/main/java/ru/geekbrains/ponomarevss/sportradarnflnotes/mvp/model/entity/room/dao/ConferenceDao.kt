package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.dao

import androidx.room.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomConference

@Dao
interface ConferenceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomConference: RoomConference)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg roomConferences: RoomConference)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomConferences: List<RoomConference>)

    @Update
    fun update(roomConference: RoomConference)

    @Update
    fun update(vararg roomConferences: RoomConference)

    @Update
    fun update(roomConferences: List<RoomConference>)

    @Delete
    fun delete(roomConference: RoomConference)

    @Delete
    fun delete(vararg roomConferences: RoomConference)

    @Delete
    fun delete(roomConferences: List<RoomConference>)

    @Query("SELECT * FROM RoomConference")
    fun getAll(): List<RoomConference>

    @Query("SELECT * FROM RoomConference WHERE id = :id LIMIT 1")
    fun findById(id: String): RoomConference?
}
