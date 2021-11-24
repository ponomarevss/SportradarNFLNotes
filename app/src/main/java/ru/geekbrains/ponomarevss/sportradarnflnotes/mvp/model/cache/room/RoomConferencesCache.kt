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
        db.conferenceDao.getAll().map { roomConference ->
            Conference(roomConference.id, roomConference.name, roomConference.alias,
                db.divisionDao.findByConferenceId(roomConference.id).map { roomDivision ->
                    Division(roomDivision.id, roomDivision.name, roomDivision.alias,
                        db.teamDao.findByDivisionId(roomDivision.id).map { roomTeam ->
                            Team(roomTeam.id, roomTeam.name, roomTeam.market, roomTeam.alias)
                        })
                })
        }
    }

    override fun putConferences(conferences: List<Conference>) = Completable.fromAction {
        val roomConferences = conferences.map { conference ->
            val roomDivision = conference.divisions.map { division ->
                val roomTeam = division.teams.map { team ->
                    RoomTeam(team.id, team.name, team.market, team.alias, division.id)
                }
                db.teamDao.insert(roomTeam)
                RoomDivision(division.id, division.name, division.alias, conference.id)
            }
            db.divisionDao.insert(roomDivision)
            RoomConference(conference.id, conference.name, conference.alias)
        }
        db.conferenceDao.insert(roomConferences)
    }
}