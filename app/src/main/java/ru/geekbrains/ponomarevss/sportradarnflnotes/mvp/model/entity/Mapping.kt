package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity

import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.games.ReGame
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.games.ReSeason
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.games.ReWeek
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.hierarchy.ReDivision
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.hierarchy.ReHierarchy
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.hierarchy.ReTeam
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.*

/**
 * Mapping of Response to General
 * */
fun mapReToGame(reGame: ReGame, teams: List<Team>) = Game(
    id = reGame.id,
    status = reGame.status,
    scheduled = reGame.scheduled,
    home = teams.first { it.id == reGame.home.id },
    away = teams.first { it.id == reGame.away.id },
//    home = reGame.home.id,
//    away = reGame.away.id,
    homePoints = reGame.scoring?.homePoints ?: 0,
    awayPoints = reGame.scoring?.awayPoints ?: 0
)

fun mapReToSeason(reSeason: ReSeason) = Season(
    id = reSeason.id,
    year = reSeason.year,
    status = reSeason.status,
    type = reSeason.type.code
)

fun mapReToTeams(reHierarchy: ReHierarchy): List<Team> =
    reHierarchy.conferences.flatMap { reConference ->
        reConference.divisions.flatMap { reDivision ->
            reDivision.teams.map { mapReToTeam(reDivision, it) }
        }
    }

fun mapReToTeam(reDivision: ReDivision, reTeam: ReTeam) = Team(
    id = reTeam.id,
    name = reTeam.name,
    market = reTeam.market,
    alias = reTeam.alias,
    division = reDivision.name
).apply { if (alias == "JAC") alias = "JAX" }

fun mapReToWeek(reWeek: ReWeek) = Week(
    id = reWeek.id,
    sequence = reWeek.sequence,
    title = reWeek.title
)

/**
 * Mapping of Room entities to General
 * */
fun mapRoomToGame(roomGame: RoomGame, teams: List<Team>) = Game(
    id = roomGame.id,
    status = roomGame.status,
    scheduled = roomGame.scheduled,
    home = teams.first { it.id == roomGame.homeId },
    away = teams.first { it.id == roomGame.awayId },
//    home = roomGame.homeId,
//    away = roomGame.awayId,
    homePoints = roomGame.homePoints,
    awayPoints = roomGame.awayPoints,
    isWatched = roomGame.isWatched
)

fun mapRoomToSeason(roomSeason: RoomSeason) = Season(
    id = roomSeason.id,
    year = roomSeason.year,
    status = roomSeason.status,
    type = roomSeason.type
)

fun mapRoomToStandings(roomStandings: RoomStandings, teams: List<Team>) = Standings(
    seasonId = roomStandings.seasonId,
    team = teams.first { it.id == roomStandings.teamId },
    wins = roomStandings.wins,
    losses = roomStandings.losses,
    ties = roomStandings.ties,
    divWins = roomStandings.divWins,
    divLosses = roomStandings.divLosses,
    divTies = roomStandings.divTies
)

fun mapRoomToTeam(roomTeam: RoomTeam) = Team(
    id = roomTeam.id,
    name = roomTeam.name,
    market = roomTeam.market,
    alias = roomTeam.alias,
    division = roomTeam.division
)

fun mapRoomToWeek(roomWeek: RoomWeek) = Week(
    id = roomWeek.id,
    sequence = roomWeek.sequence,
    title = roomWeek.title
)

/**
 * Mapping of General entities to Room
 * */
fun mapGameToRoom(game: Game, week: Week) = RoomGame(
    id = game.id,
    status = game.status,
    scheduled = game.scheduled,
    homeId = game.home.id,
    awayId = game.away.id,
    homePoints = game.homePoints,
    awayPoints = game.awayPoints,
    weekId = week.id,
    isWatched = game.isWatched
)

fun mapSeasonToRoom(season: Season) = RoomSeason(
    id = season.id,
    year = season.year,
    status = season.status,
    type = season.type
)

fun mapStandingsToRoom(standings: Standings) = RoomStandings(
    seasonId = standings.seasonId,
    teamId = standings.team.id,
    wins = standings.wins,
    losses = standings.losses,
    ties = standings.ties,
    divWins = standings.divWins,
    divLosses = standings.divLosses,
    divTies = standings.divTies
)

fun mapTeamToRoom(team: Team) = RoomTeam(
    id = team.id,
    name = team.name,
    market = team.market,
    alias = team.alias,
    division = team.division
)

fun mapWeekToRoom(week: Week, season: Season) = RoomWeek(
    id = week.id,
    sequence = week.sequence,
    title = week.title,
    seasonId = season.id
)