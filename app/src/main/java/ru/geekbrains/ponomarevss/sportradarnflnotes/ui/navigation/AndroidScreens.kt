package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.navigation

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.oldcommon.Game
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.oldcommon.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.oldcommon.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.navigation.IScreens
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.fragment.*

class AndroidScreens : IScreens {
    override fun teams(): Screen {
        TODO("Not yet implemented")
    }

    override fun table() = FragmentScreen { TableFragment.newInstance()}

    override fun seasons() = FragmentScreen { SeasonsFragment.newInstance() }

    override fun season(season: Season) = FragmentScreen { SeasonFragment.newInstance(season) }

    override fun games(season: Season, week: Week) = FragmentScreen { GamesFragment.newInstance(season, week) }

    override fun game(game: Game) = FragmentScreen { GameFragment.newInstance(game) }
}