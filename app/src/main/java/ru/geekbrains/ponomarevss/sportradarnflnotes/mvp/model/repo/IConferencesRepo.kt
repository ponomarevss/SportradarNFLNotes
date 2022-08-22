package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.oldcommon.Conference
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.oldcommon.Team

interface IConferencesRepo {
    fun getConferences(): Single<List<Conference>>
    fun getTeam(teamId: String): Single<Team>
    fun getTeams(): Single<List<Team>>
}