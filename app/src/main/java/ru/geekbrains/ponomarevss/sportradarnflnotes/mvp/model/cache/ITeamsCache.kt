package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Team

interface ITeamsCache {

    fun putTeams(teams: List<Team>): Completable
    fun getTeams(): Single<List<Team>>

    fun getTeam(teamId: String): Single<Team>
}