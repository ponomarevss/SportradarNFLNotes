package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.SeasonSchedule
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.WeeklySchedule

interface IWeekCache {
    fun getWeek(year: Int, type: String, sequence: Int): Single<Week>
    fun getWeeks(year: Int, type: String): Single<List<Week>>
    fun putWeek(weeklySchedule: WeeklySchedule): Completable
    fun putWeeks(seasonSchedule: SeasonSchedule): Completable
}