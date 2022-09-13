package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache

import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Week

interface IWeeksCache {

    suspend fun putWeeks(weeks: List<Week>, season: Season)
    suspend fun getWeeks(seasonId: String): List<Week>
}