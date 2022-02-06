package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.db

import androidx.room.RoomDatabase
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.dao.*

@androidx.room.Database(
    entities = [
        RoomLeague::class,
        RoomConference::class,
        RoomDivision::class,
        RoomTeam::class,

        RoomSeason::class,
        RoomWeek::class,
        RoomGame::class,
        RoomRival::class,
        RoomScoring::class,
        RoomPeriod::class,

        RoomStandings::class
               ],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract val leagueDao: LeagueDao
    abstract val conferenceDao: ConferenceDao
    abstract val divisionDao: DivisionDao
    abstract val teamDao: TeamDao

    abstract val seasonDao: SeasonDao
    abstract val weekDao: WeekDao
    abstract val gameDao: GameDao
    abstract val rivalDao: RivalDao
    abstract val scoringDao: ScoringDao
    abstract val periodDao: PeriodDao

    abstract val standingsDao: StandingsDao

    companion object {
        const val DB_NAME = "database.db"
    }
}
