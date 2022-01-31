package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Conference
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Team

interface IConferencesCache {
    fun getConferences(): Single<List<Conference>>
    fun putConferences(conferences: List<Conference>): Completable

    fun getTeam(teamId: String): Single<Team>
}