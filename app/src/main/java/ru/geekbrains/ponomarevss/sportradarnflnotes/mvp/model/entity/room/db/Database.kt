package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.db

import androidx.room.RoomDatabase
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.dao.*

@androidx.room.Database(
    entities = [
        RoomGame::class,
        RoomSeason::class,
        RoomStandings::class,
        RoomTeam::class,
        RoomWeek::class
    ],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract val gameDao: GameDao
    abstract val seasonDao: SeasonDao
    abstract val standingsDao: StandingsDao
    abstract val teamDao: TeamDao
    abstract val weekDao: WeekDao

    companion object {
        const val DB_NAME = "database.db"
    }
}
