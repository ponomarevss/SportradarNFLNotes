package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo

import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season

interface ISeasonsRepo {

//    suspend fun getSeasons(isOnline: Boolean): List<Season>
    suspend fun getCachedSeasons(): List<Season>
    suspend fun getNetworkSeasons(): List<Season>
}