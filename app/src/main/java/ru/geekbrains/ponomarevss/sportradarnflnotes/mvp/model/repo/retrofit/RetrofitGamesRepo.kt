package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.retrofit

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.api.IDataSource
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IGamesCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Game
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.network.INetworkStatus
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.IGamesRepo

class RetrofitGamesRepo(val api: IDataSource, private val networkStatus: INetworkStatus, val cache: IGamesCache) : IGamesRepo {

    override fun getGames(season: Season, week: Week): Single<List<Game>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getWeeklySchedule(season.year.toString(), season.type.code, week.sequence.toString())
                .flatMap {
                    val checkedGames = checkGames(it.scheduleWeek.games)
                    cache.putGames(checkedGames, week.id).toSingle { checkedGames }
                }
        } else cache.getGames(week.id)
    }.subscribeOn(Schedulers.io())

    override fun putGame(game: Game, weekId: String): Completable = cache.putGame(game, weekId).subscribeOn(Schedulers.io())

    private fun checkGames(games: List<Game>): List<Game> = games.map {
        var checkedGame = it
        cache.getGame(it.id)
            .subscribe({ game ->
                if (game.isWatched) {
                    checkedGame = game
                }
            }, {})
        checkedGame
    }
}