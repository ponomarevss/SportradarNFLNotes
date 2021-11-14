package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Conference
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Division
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Team

interface ITeamCache {
    fun getTeams(division: Division): Single<List<Team>>
    fun getTeam(id: String): Team
    fun putTeams(division: Division, teams: List<Team>): Completable
}