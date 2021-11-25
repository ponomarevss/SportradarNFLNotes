package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.room

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.ILeagueCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.ISeasonCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.League
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.SeasonSchedule
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.WeeklySchedule
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.db.Database

class RoomSeasonCache(val db: Database): ISeasonCache{
    override fun getSeason(year: Int, type: String): Single<Season> {
        TODO("Not yet implemented")
    }

    override fun putSeason(seasonSchedule: SeasonSchedule): Completable {
        TODO("Not yet implemented")
    }

    override fun putSeason(weeklySchedule: WeeklySchedule): Completable {
        TODO("Not yet implemented")
    }
}