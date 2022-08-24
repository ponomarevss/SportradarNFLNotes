package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Game
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Week

interface IGamesCache {

    fun putGames(games: List<Game>, week: Week): Completable
    fun getGames(week: Week): Single<List<Game>>

    fun putGame(game: Game, week: Week): Completable
    fun getGame(gameId: String): Single<Game>
}