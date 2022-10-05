package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.cache.room

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.cache.IStandingsCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Standings
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Team
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.mapRoomToStandings
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.mapStandingsToRoom
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.room.db.SportradarDatabase

class RoomStandingsCache(private val db: SportradarDatabase) : IStandingsCache {

    override suspend fun putStandings(standings: Standings) {
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            db.standingsDao.insert(mapStandingsToRoom(standings))
        }
    }

    override suspend fun getStandings(
        seasonId: String,
        teamId: String,
        teams: List<Team>
    ): Standings? =
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            db.standingsDao.select(seasonId, teamId)?.let { mapRoomToStandings(it, teams) }
        }

    override suspend fun putStandingsList(standingsList: List<Standings>) {
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            db.standingsDao.insert(standingsList.map { mapStandingsToRoom(it) })
        }
    }

    override suspend fun getStandingsList(seasonId: String, teams: List<Team>): List<Standings> =
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            db.standingsDao.selectForSeason(seasonId).map { mapRoomToStandings(it, teams) }
        }

}