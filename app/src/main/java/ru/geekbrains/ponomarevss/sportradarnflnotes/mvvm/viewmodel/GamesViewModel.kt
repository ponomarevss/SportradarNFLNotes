package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.cache.IGamesCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.cache.IStandingsCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Game
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Standings
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Team
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.repo.ITeamsRepo

class GamesViewModel(
    private val seasonAndWeekIds: Array<String>,
    private val gamesCache: IGamesCache,
    private val teamsRepo: ITeamsRepo,
    private val standingsCache: IStandingsCache
) : ViewModel() {

    companion object {
        private const val CLOSED_STATUS = "closed"
    }

    var teams: List<Team>? = null

    private val _mutableLiveData: MutableLiveData<List<Game>> = MutableLiveData()
    val liveData: LiveData<List<Game>> = _mutableLiveData

    fun initData() {
        viewModelScope.launch {
            initTeams()
            loadInitGames()
        }
    }

    private suspend fun initTeams() {
        if (teams == null) {
            teams = teamsRepo.getCachedTeams()
        }
    }

    private suspend fun loadInitGames() {
        if (_mutableLiveData.value == null) {
            _mutableLiveData.value = teams?.let { list ->
                gamesCache.getGames(seasonAndWeekIds[1], list).sortedBy { it.scheduled }
            }
        }
    }

    fun itemClicked(game: Game, weekId: String) {
        viewModelScope.launch {
            if (teams == null) teams = teamsRepo.getCachedTeams()
            game.apply {
                if (status == CLOSED_STATUS && !isWatched) {
                    isWatched = true
                    gamesCache.putGame(this, weekId)
                    updateStandings(seasonAndWeekIds[0], this)
                }
            }
        }
    }

    private suspend fun updateStandings(seasonId: String, game: Game) {
        val stHome = teams?.let { standingsCache.getStandings(seasonId, game.home.id, it) }
        val stAway = teams?.let { standingsCache.getStandings(seasonId, game.away.id, it) }

        game.apply {
            if (stHome != null && stAway != null) {
                when {
                    homePoints > awayPoints -> homeWins(home, away, stHome, stAway)
                    homePoints < awayPoints -> awayWins(home, away, stHome, stAway)
                    homePoints == awayPoints -> ties(home, away, stHome, stAway)
                }
                standingsCache.putStandingsList(listOf(stHome, stAway))
            }
        }
    }

    private fun homeWins(home: Team, away: Team, stHome: Standings, stAway: Standings) {
        ++stHome.wins
        ++stAway.losses
        if (home.division == away.division) {
            ++stHome.divWins
            ++stAway.divLosses
        }
    }

    private fun awayWins(home: Team, away: Team, stHome: Standings, stAway: Standings) {
        ++stHome.losses
        ++stAway.wins
        if (home.division == away.division) {
            ++stHome.divLosses
            ++stAway.divWins
        }
    }

    private fun ties(home: Team, away: Team, stHome: Standings, stAway: Standings) {
        ++stHome.ties
        ++stAway.ties
        if (home.division == away.division) {
            ++stHome.divTies
            ++stAway.divTies
        }
    }
}