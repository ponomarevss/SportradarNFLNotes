package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Game
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.SeasonSchedule
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.WeeklySchedule

interface IWeeksCache {
    fun getWeeksCache(year: Int, type: String): Single<List<Week>>
    fun putWeeksCache(seasonSchedule: SeasonSchedule): Completable

    fun getGamesCache(weekId: String): Single<List<Game>>
    fun putGamesCache(weeklySchedule: WeeklySchedule): Completable
}