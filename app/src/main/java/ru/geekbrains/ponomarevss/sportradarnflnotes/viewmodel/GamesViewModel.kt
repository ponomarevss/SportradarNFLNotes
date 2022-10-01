package ru.geekbrains.ponomarevss.sportradarnflnotes.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IStandingsCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Game
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Team
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.IGamesRepo
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.ITeamsRepo

class GamesViewModel(
    private val seasonId: String,
    private val week: Week,
    private val gamesRepo: IGamesRepo,
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
            getTeams()
            loadInitGames()
        }
    }

    private suspend fun getTeams() {
        if (teams == null) {
            teams = teamsRepo.getCachedTeams()
        }
    }

    private suspend fun loadInitGames() {
        try {
            if (_mutableLiveData.value == null) {
                _mutableLiveData.value = teams?.let { gamesRepo.getCachedGames(week, it) }
            }
        } catch (e: Throwable) {
            Log.e("AAA", "loadInitGames ${e.message.toString()}")
        }
    }

    fun itemClicked(game: Game, week: Week) {
        viewModelScope.launch {
            if (teams == null) teams = teamsRepo.getCachedTeams()
            game.apply {
                if (status == CLOSED_STATUS && !isWatched) {
                    isWatched = true
                    gamesRepo.putGame(this, week)
                    updateStandings(seasonId, this)
                }
            }
        }
    }

    private fun updateStandings(seasonId: String, game: Game) {

    }
}