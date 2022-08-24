package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.retrofit

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.api.IDataSource
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IGamesCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Game
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.mapReToGame
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.network.INetworkStatus
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.IGamesRepo

class RetrofitGamesRepo(
    private val ioScheduler: Scheduler,
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: IGamesCache
) : IGamesRepo {

    override fun getGames(season: Season, week: Week): Single<List<Game>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getWeeklySchedule(season.year.toString(), season.type, week.sequence.toString())
                .flatMap { reScheduleWeek ->
                    val checkedGames = checkGames(reScheduleWeek.week.games.map { mapReToGame(it) })
                    cache.putGames(checkedGames, week).toSingle { checkedGames }
                }
        } else cache.getGames(week)
    }.subscribeOn(ioScheduler)

    override fun putGame(game: Game, week: Week): Completable = cache.putGame(game, week).subscribeOn(ioScheduler)

    private fun checkGames(games: List<Game>): List<Game> = games.map { game ->
        var checkedGame = game
        cache.getGame(game.id)
            .subscribe(
                { if (it.isWatched) checkedGame = it },
                { println(it.message) }
            )
        checkedGame
    }
}