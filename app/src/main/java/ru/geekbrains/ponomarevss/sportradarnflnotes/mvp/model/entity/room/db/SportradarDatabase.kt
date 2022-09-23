package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.dao.GameDao
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.dao.SeasonDao
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.dao.TimestampDao
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.dao.WeekDao

@Database(
    entities = [
        RoomGame::class,
        RoomSeason::class,
//        RoomStandings::class,
//        RoomTeam::class,
        RoomWeek::class,
        RoomTimestamp::class
    ],
    version = 1
)
abstract class SportradarDatabase : RoomDatabase() {

    abstract val seasonDao: SeasonDao
    abstract val weekDao: WeekDao
    abstract val gameDao: GameDao
    abstract val timestampDao: TimestampDao

//    abstract val standingsDao: StandingsDao
//    abstract val teamDao: TeamDao

    companion object {
        const val DB_NAME = "database.db"
    }
}
