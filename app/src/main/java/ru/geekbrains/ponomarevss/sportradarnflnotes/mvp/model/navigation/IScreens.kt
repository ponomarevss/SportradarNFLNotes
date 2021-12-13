package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.navigation

import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun teams(): Screen
    fun seasons(): Screen
    fun games(): Screen
}