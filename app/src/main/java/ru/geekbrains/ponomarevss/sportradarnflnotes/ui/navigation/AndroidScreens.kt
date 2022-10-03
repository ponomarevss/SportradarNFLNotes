package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.navigation.IScreens
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.fragment.GamesFragment
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.fragment.SeasonFragment
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.fragment.SeasonsFragment

class AndroidScreens : IScreens {

    override fun seasons() = FragmentScreen { SeasonsFragment.newInstance() }

    override fun season(season: Season) = FragmentScreen { SeasonFragment.newInstance(season) }

    override fun games(seasonId: String, weekId: String) =
        FragmentScreen { GamesFragment.newInstance(seasonId, weekId) }
}