package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo

import kotlinx.coroutines.delay
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.REQUESTS_GAP
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.api.IDataSource
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IGamesCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IWeeksCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Team
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.mapReToGame
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.mapReToWeek

class WeeksRepo(
    private val api: IDataSource,
    private val weeksCache: IWeeksCache,
    private val gamesCache: IGamesCache
) : IWeeksRepo {

    override suspend fun getCachedWeeks(seasonId: String): List<Week> =
        weeksCache.getWeeks(seasonId)

    override suspend fun getApiWeeks(season: Season, teams: List<Team>): List<Week> {
        delay(REQUESTS_GAP)
        val weeks: List<Week> =
            api.getSeasonSchedule(season.year.toString(), season.type).weeks.map { reWeek ->
                val week = mapReToWeek(reWeek)
                gamesCache.putGames(reWeek.games.map { mapReToGame(it, teams) }, week.id)
                week
            }
        weeksCache.putWeeks(weeks, season.id)
        return weeks
    }
}