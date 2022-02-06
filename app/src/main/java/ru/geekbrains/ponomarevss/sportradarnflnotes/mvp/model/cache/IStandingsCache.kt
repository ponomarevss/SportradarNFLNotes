package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Standings

interface IStandingsCache {
    fun putStandings(standings: Standings): Completable
    fun getStandings(seasonId: String, teamId: String): Single<Standings>
}