package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Conference
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.League

interface IConferencesCache {
    fun getConferences(league: League): Single<List<Conference>>
    fun putConferences(league: League, conferences: List<Conference>): Completable
}