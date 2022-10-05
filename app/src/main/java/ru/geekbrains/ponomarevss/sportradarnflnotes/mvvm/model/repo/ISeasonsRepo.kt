package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.repo

import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Season

interface ISeasonsRepo {

    suspend fun getCachedSeasons(): List<Season>
    suspend fun getApiSeasons(): List<Season>
}