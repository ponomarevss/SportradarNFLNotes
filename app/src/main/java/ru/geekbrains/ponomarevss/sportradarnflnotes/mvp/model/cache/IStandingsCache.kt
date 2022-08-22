package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.oldcommon.Standings

interface IStandingsCache {
    fun putStandings(standings: Standings): Completable
    fun getStandings(seasonId: String, teamId: String): Single<Standings>

    fun putStandingsList(standings: List<Standings>): Completable
    fun getStandingsList(seasonId: String): Single<List<Standings>>
}