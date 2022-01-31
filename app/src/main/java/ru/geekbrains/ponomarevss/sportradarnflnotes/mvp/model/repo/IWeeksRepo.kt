package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Week

interface IWeeksRepo {
    fun getWeeks(season: Season): Single<List<Week>>
}