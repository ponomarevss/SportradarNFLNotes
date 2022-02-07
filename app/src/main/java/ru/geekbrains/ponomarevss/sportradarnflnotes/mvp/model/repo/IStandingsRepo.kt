package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Standings

interface IStandingsRepo {
    fun getStandings(seasonId: String, teamId: String): Single<Standings>
    fun getStandingsList(seasonId: String): Single<List<Standings>>
    fun putStandings(standings: Standings): Completable
}