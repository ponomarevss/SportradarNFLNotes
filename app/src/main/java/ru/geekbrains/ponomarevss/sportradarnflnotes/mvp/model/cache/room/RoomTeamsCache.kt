package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.room

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.ITeamsCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Team
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.mapRoomToTeam
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.mapTeamToRoom
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.db.Database

class RoomTeamsCache(private val db: Database) : ITeamsCache {

    override fun putTeams(teams: List<Team>): Completable = Completable.fromAction {
        db.teamDao.insert(teams.map { mapTeamToRoom(it) })
    }

    override fun getTeams(): Single<List<Team>> = Single.fromCallable {
        db.teamDao.getAll().map { mapRoomToTeam(it) }
    }

    override fun getTeam(teamId: String): Single<Team> = Single.fromCallable {
        db.teamDao.findById(teamId)?.let { mapRoomToTeam(it) }
    }
}