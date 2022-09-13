package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.games.ReLeagueSeasons
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.games.ReScheduleSeason
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.games.ReScheduleWeek
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.hierarchy.ReHierarchy

interface IDataSource {

    companion object {
        private const val APIKEY = "efv8g6v7bta5mkgey4v38xbe"
    }

    @GET("league/seasons.json")
    suspend fun getSeasons(@Query("api_key") apiKey: String = APIKEY): ReLeagueSeasons

    @GET("league/hierarchy.json")
    suspend fun getLeagueHierarchy(
        @Query("api_key") apiKey: String = APIKEY
    ): ReHierarchy

    @GET("games/{year}/{nfl_season}/schedule.json")
    suspend fun getSeasonSchedule(
        @Path("year") year: String,
        @Path("nfl_season") nflSeason: String,
        @Query("api_key") apiKey: String = APIKEY
    ): ReScheduleSeason

    @GET("games/{year}/{nfl_season}/{nfl_season_week}/schedule.json")
    suspend fun getWeeklySchedule(
        @Path("year") year: String,
        @Path("nfl_season") nflSeason: String,
        @Path("nfl_season_week") nflSeasonWeek: String,
        @Query("api_key") apiKey: String = APIKEY
    ): ReScheduleWeek
}


//    https://api.sportradar.us/nfl/official/trial/v7/en/games/2021/REG/01/schedule.json?api_key=efv8g6v7bta5mkgey4v38xbe
//    https://api.sportradar.us/nfl/official/trial/v7/en/games/2021/REG/schedule.json?api_key=efv8g6v7bta5mkgey4v38xbe
//    https://api.sportradar.us/nfl/official/trial/v7/en/league/hierarchy.json?api_key=efv8g6v7bta5mkgey4v38xbe
//    http://api.sportradar.us/nfl/official/trial/v7/en/league/seasons.json?api_key=efv8g6v7bta5mkgey4v38xbe