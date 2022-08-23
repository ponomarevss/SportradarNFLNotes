package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity

import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.games.ReGame
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.games.ReSeason
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.games.ReWeek
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.hierarchy.ReDivision
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.hierarchy.ReTeam
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.*

//Re to General
fun mapReToGame(reGame: ReGame) = Game(
    id = reGame.id,
    status = reGame.status,
    scheduled = reGame.scheduled,
    home = reGame.home.id,
    away = reGame.away.id,
    scoring = reGame.scoring?.let { listOf(it.homePoints, it.awayPoints) } ?: listOf(0, 0)
)

fun mapReToSeason(reSeason: ReSeason) = Season(
    id = reSeason.id,
    year = reSeason.year,
    status = reSeason.status,
    type = reSeason.type.code
)

fun mapReToTeam(reDivision: ReDivision, reTeam: ReTeam) = Team(
    id = reTeam.id,
    name = reTeam.name,
    market = reTeam.market,
    alias = reTeam.alias,
    division = reDivision.name
)

fun mapReToWeek(reWeek: ReWeek) = Week(
    id = reWeek.id,
    sequence = reWeek.sequence,
    title = reWeek.title
)

//Room to General
fun mapRoomToGame(roomGame: RoomGame, roomScoring: RoomScoring) = Game(
    id = roomGame.id,
    status = roomGame.status,
    scheduled = roomGame.scheduled,
    home = roomGame.homeId,
    away = roomGame.awayId,
    scoring = roomScoring.let { listOf(it.homePoints, it.awayPoints) },
    isWatched = roomGame.isWatched
)

fun mapRoomToSeason(roomSeason: RoomSeason) = Season(
    id = roomSeason.id,
    year = roomSeason.year,
    status = roomSeason.status,
    type = roomSeason.type
)

fun mapRoomToStandings(roomStandings: RoomStandings) = Standings(
    seasonId = roomStandings.seasonId,
    teamId = roomStandings.teamId,
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

//General to Room
fun mapGameToRoomGame(game: Game, week: Week) = RoomGame(
    id = game.id,
    status = game.status,
    scheduled = game.scheduled,
    homeId = game.home,
    awayId = game.away,
    weekId = week.id,
    isWatched = game.isWatched
)

fun mapGameToRoomScoring(game: Game) = RoomScoring(
    id = game.id,
    homePoints = game.scoring[0],
    awayPoints = game.scoring[1],
    gameId = game.id
)

//todo JAX re to team

//Re to Room