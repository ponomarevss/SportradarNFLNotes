package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.room

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IConferencesCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Conference
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Division
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Team
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomConference
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomDivision
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomTeam
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.db.Database

class RoomConferencesCache(val db: Database) : IConferencesCache {

    override fun putConferences(conferences: List<Conference>) = Completable.fromAction {
        conferences.map {
            db.conferenceDao.insert(RoomConference(it.id, it.name, it.alias))
            putDivisions(it)
        }
    }

    override fun getConferences(): Single<List<Conference>> = Single.fromCallable {
        db.conferenceDao.getAll().map {
            Conference(it.id, it.name, it.alias, getDivisions(it.id))
        }
    }

    override fun getTeam(teamId: String): Single<Team> = Single.fromCallable {
        db.teamDao.findById(teamId)?.let {
            Team(it.id, it.name, it.market, it.alias)
        }
    }

    private fun putDivisions(conference: Conference) = conference.divisions.map {
        db.divisionDao.insert(RoomDivision(it.id, it.name, it.alias, conference.id))
        putTeams(it)
    }

    private fun putTeams(division: Division) = division.teams.map {
        RoomTeam(it.id, it.name, it.market, it.alias, division.id)
    }.let { db.teamDao.insert(it) }


    private fun getDivisions(conferenceId: String) =
        db.divisionDao.findByConferenceId(conferenceId).map {
            Division(it.id, it.name, it.alias, getTeams(it.id))
        }

    private fun getTeams(divisionId: String) =
        db.teamDao.findByDivisionId(divisionId).map {
            Team(it.id, it.name, it.market, it.alias)
        }
}