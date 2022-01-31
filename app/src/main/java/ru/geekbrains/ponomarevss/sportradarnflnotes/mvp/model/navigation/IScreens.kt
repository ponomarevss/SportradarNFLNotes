package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.navigation

import com.github.terrakok.cicerone.Screen
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Game
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Week

interface IScreens {
    fun teams(): Screen
    fun table(): Screen
    fun seasons(): Screen
    fun season(season: Season): Screen
    fun games(season: Season, week: Week): Screen
    fun game(game: Game): Screen
}