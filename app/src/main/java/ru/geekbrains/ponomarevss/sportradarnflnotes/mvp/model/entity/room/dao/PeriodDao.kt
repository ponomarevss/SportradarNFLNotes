package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.dao

import androidx.room.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomConference
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomDivision
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomPeriod

@Dao
interface PeriodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomPeriod: RoomPeriod)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg roomPeriods: RoomPeriod)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomPeriods: List<RoomPeriod>)

    @Update
    fun update(roomPeriod: RoomPeriod)

    @Update
    fun update(vararg roomPeriods: RoomPeriod)

    @Update
    fun update(roomPeriods: List<RoomPeriod>)

    @Delete
    fun delete(roomPeriod: RoomPeriod)

    @Delete
    fun delete(vararg roomPeriods: RoomPeriod)

    @Delete
    fun delete(roomPeriods: List<RoomPeriod>)

//    @Query("SELECT * FROM RoomPeriod")
//    fun getAll(): List<RoomPeriod>

    @Query("SELECT * FROM RoomPeriod WHERE scoringId = :scoringId")
    fun select(scoringId: String): List<RoomPeriod>
}
