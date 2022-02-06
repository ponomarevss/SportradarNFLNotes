package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.room

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IConferencesCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IStandingsCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Conference
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Division
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Standings
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Team
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomConference
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomDivision
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomStandings
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomTeam
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.db.Database

class RoomStandingsCache(val db: Database) : IStandingsCache {

    override fun putStandings(standings: Standings): Completable = Completable.fromAction {
        with(standings) { db.standingsDao.insert(RoomStandings(seasonId, teamId, wins, losses, ties, divWins, divLosses, divTies)) }
    }

    override fun getStandings(seasonId: String, teamId: String): Single<Standings> = Single.fromCallable {
        db.standingsDao.select(seasonId, teamId)?.run {
            Standings(seasonId, teamId, wins, losses, ties, divWins, divLosses, divTies)
        } ?: Standings(seasonId, teamId)
    }

}