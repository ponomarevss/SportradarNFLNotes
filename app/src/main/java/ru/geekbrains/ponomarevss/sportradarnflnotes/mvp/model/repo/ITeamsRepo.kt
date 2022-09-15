package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Team

interface ITeamsRepo {

    fun getTeam(teamId: String): Single<Team>
//    fun getTeams(): Single<List<Team>>
}