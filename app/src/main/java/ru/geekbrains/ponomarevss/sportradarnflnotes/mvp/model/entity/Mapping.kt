package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity

import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Game
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.games.ReGame

//Re to General
fun mapReToGame(reGame: ReGame) = Game(
    id = reGame.id,
    status = reGame.status,
    scheduled = reGame.scheduled,
    home = reGame.home.id,
    away = reGame.away.id,
    scoring = reGame.scoring?.let { listOf(it.homePoints, it.awayPoints) }
)

//Room to General

//General to Room

//Re to Room