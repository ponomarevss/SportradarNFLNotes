package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Conference
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.League
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Season

interface ISeasonsCache {
    fun putSeasons(seasons: List<Season>): Completable
    //    fun getSeason(year: String, type: String): Single<Season>
    fun getSeasons(): Single<List<Season>>
}