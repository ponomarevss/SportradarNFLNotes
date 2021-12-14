package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.navigation

import com.github.terrakok.cicerone.Screen
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Week

interface IScreens {
    fun teams(): Screen
    fun seasons(): Screen
    fun weeks(season: Season): Screen
    fun games(week: Week): Screen
}