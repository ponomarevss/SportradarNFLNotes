package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.viewmodel

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.HOURLY_UPDATE
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.cache.IGamesCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.cache.IStandingsCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.cache.ITimestampCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Standings
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Team
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.repo.ITeamsRepo
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.repo.IWeeksRepo

class SeasonViewModel(
    private val season: Season,
    private val weeksRepo: IWeeksRepo,
    private val teamsRepo: ITeamsRepo,
    private val standingsCache: IStandingsCache,
    private val timestampCache: ITimestampCache,
    private val gamesCache: IGamesCache
) : ViewModel() {

    private val _weeksMutableLiveData: MutableLiveData<List<Week>> = MutableLiveData()
    val weeksLiveData: LiveData<List<Week>> = _weeksMutableLiveData

    private val _standingsMutableLiveData: MutableLiveData<List<Standings>?> = MutableLiveData()
    val standingsLiveData: LiveData<List<Standings>?> = _standingsMutableLiveData

    private var teams: List<Team> = mutableListOf()

    fun loadInitData() {
        viewModelScope.launch {
            initTeams()
            loadInitWeeks()
            isWeekWatched()
            collectStandingsList()
        }
    }

    fun onlineLiveDataObserver(): Observer<Boolean> = Observer<Boolean> {
        updateWeeks(it)
    }

    private suspend fun initTeams() {
        teams = teamsRepo.getCachedTeams()
    }

    private suspend fun loadInitWeeks() {
        try {
            if (_weeksMutableLiveData.value == null) {
                _weeksMutableLiveData.value = weeksRepo.getCachedWeeks(season.id)
            }
        } catch (e: Throwable) {
            Log.e("AAA", "loadInitWeeks Throwable: ${e.message.toString()}")
        }
    }

    private suspend fun collectStandingsList() {
        standingsCache.observeStandingsList(season.id, teams)
            .collect { standingsList ->
                if (standingsList.isEmpty()) createInitialStandingsList(teams)
                _standingsMutableLiveData.value = standingsList.sortedBy { it.team.division }
            }
    }

    private suspend fun createInitialStandingsList(teams: List<Team>): List<Standings> {
        val initialList = teams.map { Standings(seasonId = season.id, team = it) }
        standingsCache.putStandingsList(initialList)
        return initialList
    }

    private fun updateWeeks(isOnline: Boolean) {
        viewModelScope.launch {
            try {
                if (isOnline && !timestampCache.isUpdated(season.id, HOURLY_UPDATE)) {
                    _weeksMutableLiveData.value = weeksRepo.getApiWeeks(season, teams)
                    timestampCache.updateTimestamp(season.id)
                }
            } catch (e: Throwable) {
                Log.e("AAA", "updateWeeks Throwable: ${e.message.toString()}")
            }
        }
    }

    fun setWeekWatched(weekId: String) {}

    private fun isWeekWatched(/*weekId: String*/): Boolean {
        var isWeekWatched = false

        viewModelScope.launch {
//            gamesCache.getGames(weekId, teams)
//            isWeekWatched = true
        }
        return isWeekWatched
    }
}