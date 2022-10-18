package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.room.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.room.dao.*

@Database(
    entities = [
        RoomGame::class,
        RoomSeason::class,
        RoomStandings::class,
        RoomTeam::class,
        RoomWeek::class,
        RoomTimestamp::class
    ],
    version = 2
)
abstract class SportradarDatabase : RoomDatabase() {

    abstract val seasonDao: SeasonDao
    abstract val weekDao: WeekDao
    abstract val gameDao: GameDao
    abstract val timestampDao: TimestampDao
    abstract val teamDao: TeamDao
    abstract val standingsDao: StandingsDao

    companion object {
        const val DB_NAME = "database.db"
    }
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE RoomGame ADD COLUMN rating FLOAT NOT NULL DEFAULT 0")
    }

}
