package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.repo

import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Team
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Week

interface IWeeksRepo {

    suspend fun getCachedWeeks(seasonId: String): List<Week>
    suspend fun getApiWeeks(season: Season, teams: List<Team>): List<Week>
}