package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Game
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.SeasonSchedule
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.WeeklySchedule

interface IGamesCache {
    fun putGames(games: List<Game>, weekId: String): Completable
    fun getGames(weekId: String): Single<List<Game>>

    fun putGame(game: Game, weekId: String): Completable
    fun getGame(gameId: String): Single<Game>
}