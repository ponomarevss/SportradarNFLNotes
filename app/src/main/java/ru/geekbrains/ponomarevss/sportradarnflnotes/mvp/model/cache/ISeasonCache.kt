package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.SeasonSchedule
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.WeeklySchedule

interface ISeasonCache {
    fun getSeason(year: Int, type: String): Single<Season>
    fun putSeason(seasonSchedule: SeasonSchedule): Completable
    fun putSeason(weeklySchedule: WeeklySchedule): Completable
}