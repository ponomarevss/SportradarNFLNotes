package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.SeasonSchedule
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.WeeklySchedule

interface IWeekCache {
    fun getWeek(season: Season, sequence: Int): Single<Week>
    fun getWeeks(season: Season): Single<List<Week>>
    fun putWeek(weeklySchedule: WeeklySchedule, week: Week): Completable
    fun putWeeks(seasonSchedule: SeasonSchedule, weeks: List<Week>): Completable
}