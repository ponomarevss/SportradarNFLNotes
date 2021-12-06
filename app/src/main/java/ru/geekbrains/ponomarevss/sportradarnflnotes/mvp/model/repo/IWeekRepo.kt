package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Week

interface IWeekRepo {
    fun getWeek(year: Int, type: String, sequence: Int): Single<Week>
    fun getWeeks(year: Int, type: String): Single<List<Week>>
}