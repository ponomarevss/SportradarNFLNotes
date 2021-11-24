package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.room

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.ILeagueCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.League
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.db.Database

class RoomLeagueCache(val db: Database): ILeagueCache{
    override fun getLeague(): Single<League> {
        TODO("Not yet implemented")
    }

    override fun putLeague(league: League): Completable {
        TODO("Not yet implemented")
    }
}