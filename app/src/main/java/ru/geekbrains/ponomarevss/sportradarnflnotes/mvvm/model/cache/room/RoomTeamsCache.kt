package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.cache.room

import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.cache.ITeamsCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Team
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.mapRoomToTeam
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.mapTeamToRoom
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.room.db.SportradarDatabase

class RoomTeamsCache(private val db: SportradarDatabase) : ITeamsCache {

    override suspend fun putTeams(teams: List<Team>) {
        db.teamDao.insert(teams.map { mapTeamToRoom(it) })
    }

    override suspend fun getTeams(): List<Team> =
        db.teamDao.getAll().map { mapRoomToTeam(it) }

    override suspend fun getTeam(teamId: String): Team? =
        db.teamDao.findById(teamId)?.let { mapRoomToTeam(it) }
}