package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Standings

interface IStandingsRepo {

    fun putStandings(standings: Standings): Completable
    fun getStandings(seasonId: String, teamId: String): Single<Standings>

    fun putStandingsList(standingsList: List<Standings>): Completable
    fun getStandingsList(seasonId: String): Single<List<Standings>>
}