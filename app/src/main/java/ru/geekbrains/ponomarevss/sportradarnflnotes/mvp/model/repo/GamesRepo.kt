package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo

import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.api.IDataSource
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IGamesCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Game
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Team

class GamesRepo(private val api: IDataSource, private val cache: IGamesCache) : IGamesRepo {

//    override suspend fun getApiGames(season: Season, week: Week, teams: List<Team>): List<Game> {
//        delay(REQUESTS_GAP)
//        var games: List<Game> = api.getWeeklySchedule(
//            season.year.toString(),
//            season.type,
//            week.sequence.toString()
//        ).week.games.map { mapReToGame(it, teams) }
//        games = checkGames(games, teams)
//        cache.putGames(games, week.id)
//        return games
//    }

    override suspend fun getCachedGames(weekId: String, teams: List<Team>): List<Game> =
        cache.getGames(weekId, teams)

    override suspend fun putGame(game: Game, weekId: String) {
        cache.putGame(game, weekId)
    }
}