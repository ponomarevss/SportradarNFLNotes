package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.dao

import androidx.room.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomConference
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomDivision
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomRival
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomTeam

@Dao
interface RivalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomRival: RoomRival)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg roomRivals: RoomRival)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomRivals: List<RoomRival>)

    @Update
    fun update(roomRival: RoomRival)

    @Update
    fun update(vararg roomRivals: RoomRival)

    @Update
    fun update(roomRivals: List<RoomRival>)

    @Delete
    fun delete(roomRival: RoomRival)

    @Delete
    fun delete(vararg roomRivals: RoomRival)

    @Delete
    fun delete(roomRivals: List<RoomRival>)

//    @Query("SELECT * FROM RoomRival")
//    fun getAll(): List<RoomRival>?

    @Query("SELECT * FROM RoomRival WHERE gameId = :gameId AND status = 'home' LIMIT 1")
    fun findHomeByGameId(gameId: String): RoomRival?

    @Query("SELECT * FROM RoomRival WHERE gameId = :gameId AND status = 'away' LIMIT 1")
    fun findAwayByGameId(gameId: String): RoomRival?
}
