package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity

import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Game
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Team
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.games.ReGame
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.games.ReSeason
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.games.ReWeek
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.hierarchy.ReDivision
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.hierarchy.ReTeam

//Re to General
fun mapReToGame(reGame: ReGame) = Game(
    id = reGame.id,
    status = reGame.status,
    scheduled = reGame.scheduled,
    home = reGame.home.id,
    away = reGame.away.id,
    scoring = reGame.scoring?.let { listOf(it.homePoints, it.awayPoints) }
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


//General to Room

//Re to Room