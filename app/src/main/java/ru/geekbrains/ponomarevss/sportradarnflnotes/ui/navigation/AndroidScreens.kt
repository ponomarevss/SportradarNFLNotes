package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.navigation

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Game
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.navigation.IScreens
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.fragment.*

class AndroidScreens : IScreens {
    override fun teams(): Screen {
        TODO("Not yet implemented")
    }

    override fun table() = FragmentScreen { TableFragment.newInstance()}

    override fun seasons() = FragmentScreen { SeasonsFragment.newInstance() }

    override fun weeks(season: Season) = FragmentScreen { WeeksFragment.newInstance(season) }

    override fun games(season: Season, week: Week) = FragmentScreen { GamesFragment.newInstance(season, week) }

    override fun game(game: Game) = FragmentScreen { GameFragment.newInstance(game) }
}