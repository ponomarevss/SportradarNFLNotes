package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.room

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IStandingsCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Standings
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.mapRoomToStandings
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.mapStandingsToRoom
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.db.Database

class RoomStandingsCache(private val db: Database) : IStandingsCache {

    override fun putStandings(standings: Standings): Completable = Completable.fromAction {
        db.standingsDao.insert(mapStandingsToRoom(standings))
    }

    override fun getStandings(seasonId: String, teamId: String): Single<Standings> = Single.fromCallable {
        db.standingsDao.select(seasonId, teamId)?.let { mapRoomToStandings(it) }
    }

    override fun putStandingsList(standingsList: List<Standings>): Completable = Completable.fromAction {
        db.standingsDao.insert(standingsList.map { mapStandingsToRoom(it) })
    }

    override fun getStandingsList(seasonId: String): Single<List<Standings>> = Single.fromCallable {
        db.standingsDao.selectForSeason(seasonId).map { mapRoomToStandings(it) }
    }
}