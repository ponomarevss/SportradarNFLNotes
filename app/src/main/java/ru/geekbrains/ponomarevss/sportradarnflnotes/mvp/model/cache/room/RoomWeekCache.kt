package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.room

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.ILeagueCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.ISeasonCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IWeekCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.db.Database

class RoomWeekCache(val db: Database): IWeekCache{
    override fun getWeek(year: Int, type: String, sequence: Int): Single<Week> = Single.fromCallable {

    }

    override fun getWeeks(year: Int, type: String): Single<List<Week>> {
        TODO("Not yet implemented")
    }

    override fun putWeek(weeklySchedule: WeeklySchedule): Completable {
        TODO("Not yet implemented")
    }

    override fun putWeeks(seasonSchedule: SeasonSchedule): Completable {
        TODO("Not yet implemented")
    }
}