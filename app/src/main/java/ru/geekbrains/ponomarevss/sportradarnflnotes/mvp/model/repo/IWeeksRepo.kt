package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo

import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Week

interface IWeeksRepo {

    suspend fun getCachedWeeks(season: Season): List<Week>
    suspend fun getApiWeeks(season: Season): List<Week>
}