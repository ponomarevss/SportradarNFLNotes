package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.cache.room

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.cache.IStandingsCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Standings
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Team
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.mapRoomToStandings
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.mapStandingsToRoom
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.room.db.SportradarDatabase

class RoomStandingsCache(private val db: SportradarDatabase) : IStandingsCache {

    override suspend fun putStandings(standings: Standings) {
        db.standingsDao.insert(mapStandingsToRoom(standings))
    }

    override suspend fun getStandings(
        seasonId: String,
        teamId: String,
        teams: List<Team>
    ): Standings? =
        db.standingsDao.select(seasonId, teamId)?.let { mapRoomToStandings(it, teams) }

    override suspend fun putStandingsList(standingsList: List<Standings>) {
        db.standingsDao.insert(standingsList.map { mapStandingsToRoom(it) })
    }

    override suspend fun getStandingsList(seasonId: String, teams: List<Team>): List<Standings> =
        db.standingsDao.selectForSeason(seasonId).map { mapRoomToStandings(it, teams) }

    override fun observeStandingsList(seasonId: String, teams: List<Team>): Flow<List<Standings>> =
        db.standingsDao.observeForSeason(seasonId).map { list ->
            list.map { mapRoomToStandings(it, teams) }
        }
}