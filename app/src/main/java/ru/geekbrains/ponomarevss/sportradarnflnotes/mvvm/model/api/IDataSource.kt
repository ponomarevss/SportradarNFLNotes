package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.response.games.ReLeagueSeasons
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.response.games.ReScheduleSeason
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.response.hierarchy.ReHierarchy

interface IDataSource {

    companion object {
        private const val APIKEY = "vkmxt8k2jjgxnxvr82usdrdk"
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
}


//    https://api.sportradar.us/nfl/official/trial/v7/en/games/2021/REG/01/schedule.json?api_key=efv8g6v7bta5mkgey4v38xbe
//    https://api.sportradar.us/nfl/official/trial/v7/en/games/2021/REG/schedule.json?api_key=efv8g6v7bta5mkgey4v38xbe
//    https://api.sportradar.us/nfl/official/trial/v7/en/league/hierarchy.json?api_key=efv8g6v7bta5mkgey4v38xbe
//    http://api.sportradar.us/nfl/official/trial/v7/en/league/seasons.json?api_key=efv8g6v7bta5mkgey4v38xbe