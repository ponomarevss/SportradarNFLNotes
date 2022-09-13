package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache

import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season

interface ISeasonsCache {

    suspend fun putSeasons(seasons: List<Season>)
    suspend fun getSeasons(): List<Season>
}