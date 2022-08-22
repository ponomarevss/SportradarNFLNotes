package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.oldcommon.Game
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.oldcommon.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.oldcommon.Week

interface IGamesRepo {
    fun getGames(season: Season, week: Week): Single<List<Game>>
    fun putGame(game: Game, weekId: String): Completable
}