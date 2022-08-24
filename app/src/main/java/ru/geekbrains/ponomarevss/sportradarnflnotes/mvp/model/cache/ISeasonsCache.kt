package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season

interface ISeasonsCache {

    fun putSeasons(seasons: List<Season>): Completable
    fun getSeasons(): Single<List<Season>>
}