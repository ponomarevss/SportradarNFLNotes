package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.repo

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.REQUESTS_GAP
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.api.IDataSource
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.cache.IGamesCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.cache.IWeeksCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Game
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Team
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.mapReToGame
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.mapReToWeek

class WeeksRepo(
    private val api: IDataSource,
    private val weeksCache: IWeeksCache,
    private val gamesCache: IGamesCache
) : IWeeksRepo {

    override suspend fun getCachedWeeks(seasonId: String): List<Week> =
        weeksCache.getWeeks(seasonId)

    override suspend fun handleApiData(season: Season, teams: List<Team>): List<Week> {
        delay(REQUESTS_GAP)
        val reWeeks = api.getSeasonSchedule(season.year.toString(), season.type).weeks
        reWeeks.forEach { reWeek ->
            val games = reWeek.games.map { mapReToGame(it, teams) }
            gamesCache.putGames(checkGames(games, teams), reWeek.id)
        }
        val weeks = reWeeks.map { mapReToWeek(it) }
        weeksCache.putWeeks(weeks, season.id)

        return weeks
    }

    private suspend fun checkGames(games: List<Game>, teams: List<Team>): List<Game> {
        games as MutableList<Game>
        games.map { game ->
            gamesCache.getGame(game.id, teams)?.let {
                if (it.isWatched) {
                    games.remove(game)
                }
            }
        }
        return games
    }
}