package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.navigation

import com.github.terrakok.cicerone.Screen
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Season

interface IScreens {
    fun seasons(): Screen
    fun season(season: Season): Screen
    fun games(seasonId: String, weekId: String): Screen
}