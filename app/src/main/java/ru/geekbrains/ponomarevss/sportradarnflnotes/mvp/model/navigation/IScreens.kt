package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.navigation

import com.github.terrakok.cicerone.Screen
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season

interface IScreens {
    fun seasons(): Screen
    fun season(season: Season): Screen
    fun games(seasonId: String, weekId: String): Screen
}