package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.cache

import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Week

interface IWeeksCache {

    suspend fun putWeeks(weeks: List<Week>, seasonId: String)
    suspend fun getWeeks(seasonId: String): List<Week>
}