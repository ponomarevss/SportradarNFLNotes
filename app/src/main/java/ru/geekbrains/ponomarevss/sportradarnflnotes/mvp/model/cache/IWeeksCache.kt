package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.oldcommon.Week

interface IWeeksCache {
    fun putWeeks(weeks: List<Week>, seasonId: String): Completable
    fun getWeeks(seasonId: String): Single<List<Week>>
}