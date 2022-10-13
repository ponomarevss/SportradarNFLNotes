package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.repo

import android.util.Log
import kotlinx.coroutines.delay
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
            gamesCache.putGames(filterWatchedGames(games, reWeek.id, teams), reWeek.id)
        }
        val weeks = reWeeks.map { mapReToWeek(it) }
        weeksCache.putWeeks(weeks, season.id)
        return weeks
    }

    private suspend fun filterWatchedGames(
        games: List<Game>,
        weekId: String,
        teams: List<Team>
    ): List<Game> {
        val filteredGames = mutableListOf<Game>()
        val cachedGames = gamesCache.getGames(weekId, teams)
        return if (cachedGames.isNotEmpty()) {
            games.forEach { game ->
                if (!cachedGames.first { it.id == game.id }.isWatched) {
                    filteredGames.add(game)
                }
            }
            filteredGames
        } else {
            games
        }
    }
}