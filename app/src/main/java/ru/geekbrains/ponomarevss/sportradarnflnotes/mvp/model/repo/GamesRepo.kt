package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.REQUESTS_GAP
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.api.IDataSource
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IGamesCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Game
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.mapReToGame

class GamesRepo(private val api: IDataSource, private val cache: IGamesCache) : IGamesRepo {

    override suspend fun getApiGames(season: Season, week: Week): List<Game> =
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            delay(REQUESTS_GAP)
            val games: List<Game> =
                api.getWeeklySchedule(
                    season.year.toString(),
                    season.type,
                    week.sequence.toString()
                ).week.games.map {
                    mapReToGame(it)
                }
            cache.putGames(games, week)
            games
        }

    override suspend fun getCachedGames(week: Week): List<Game> =
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            cache.getGames(week)
        }

    override suspend fun putGame(game: Game, week: Week) {
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            cache.putGame(game, week)
        }
    }

//    private fun checkGames(games: List<Game>): List<Game> = games.map { game ->
//        var checkedGame = game
//        cache.getGame(game.id)
//            .subscribe(
//                { if (it.isWatched) checkedGame = it },
//                { println(it.message) }
//            )
//        checkedGame
//    }
}