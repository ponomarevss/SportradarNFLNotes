package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.room

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IConferencesCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Conference
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Division
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Team
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomConference
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomDivision
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomTeam
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.db.Database

class RoomConferencesCache(val db: Database) : IConferencesCache {

    override fun getConferences(): Single<List<Conference>> = Single.fromCallable {
        db.conferenceDao.getAll().map {
            Conference(it.id, it.name, it.alias, getDivisions(it.id))
        }
    }

    override fun putConferences(conferences: List<Conference>) = Completable.fromAction {
        conferences.map {
            putDivisions(it)
            RoomConference(it.id, it.name, it.alias)
        }.let { db.conferenceDao.insert(it) }
    }

    private fun getDivisions(conferenceId: String) =
        db.divisionDao.findByConferenceId(conferenceId).map {
            Division(it.id, it.name, it.alias, getTeams(it.id))
        }

    private fun getTeams(divisionId: String) =
        db.teamDao.findByDivisionId(divisionId).map {
            Team(it.id, it.name, it.market, it.alias)
        }

    private fun putDivisions(conference: Conference) = conference.divisions.map {
        putTeams(it)
        RoomDivision(it.id, it.name, it.alias, conference.id)
    }.let { db.divisionDao.insert(it) }

    private fun putTeams(division: Division) = division.teams.map {
        RoomTeam(it.id, it.name, it.market, it.alias, division.id)
    }.let { db.teamDao.insert(it) }

}

//    override fun getConferences(): Single<List<Conference>> = Single.fromCallable {
//        db.conferenceDao.getAll().map { roomConference ->
//            Conference(roomConference.id, roomConference.name, roomConference.alias,
//                db.divisionDao.findByConferenceId(roomConference.id).map { roomDivision ->
//                    Division(roomDivision.id, roomDivision.name, roomDivision.alias,
//                        db.teamDao.findByDivisionId(roomDivision.id).map { roomTeam ->
//                            Team(roomTeam.id, roomTeam.name, roomTeam.market, roomTeam.alias)
//                        })
//                })
//        }
//    }

//    override fun putConferences(conferences: List<Conference>) = Completable.fromAction {
//        val roomConferences = conferences.map { conference ->
//            val roomDivisions = conference.divisions.map { division ->
//                val roomTeams = division.teams.map { team ->
//                    RoomTeam(team.id, team.name, team.market, team.alias, division.id)
//                }
//                db.teamDao.insert(roomTeams)
//                RoomDivision(division.id, division.name, division.alias, conference.id)
//            }
//            db.divisionDao.insert(roomDivisions)
//            RoomConference(conference.id, conference.name, conference.alias)
//        }
//        db.conferenceDao.insert(roomConferences)
//    }

