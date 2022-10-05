package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.viewmodel

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.HOURLY_UPDATE
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
    private val timestampCache: ITimestampCache
) : ViewModel() {

    private val _weeksMutableLiveData: MutableLiveData<List<Week>> = MutableLiveData()
    val weeksLiveData: LiveData<List<Week>> = _weeksMutableLiveData

    private val _standingsMutableLiveData: MutableLiveData<List<Standings>?> = MutableLiveData()
    val standingsLiveData: LiveData<List<Standings>?> = _standingsMutableLiveData

    private var teams: List<Team>? = null

    fun loadInitData() {
        viewModelScope.launch {
            initTeams()
            loadInitWeeks()
            collectStandingsList()
        }
    }

    fun onlineLiveDataObserver(): Observer<Boolean> = Observer<Boolean> {
        updateWeeks(it)
    }

    private suspend fun initTeams() {
        if (teams == null) teams = teamsRepo.getCachedTeams()
    }

    private suspend fun loadInitWeeks() {
        try {
            if (_weeksMutableLiveData.value == null) {
                _weeksMutableLiveData.value = weeksRepo.getCachedWeeks(season.id)
            }
        } catch (e: Throwable) {
            Log.e("AAA", "loadInitWeeks ${e.message.toString()}")
        }
    }

    private suspend fun collectStandingsList() {
        teams?.let { teamsList ->
            standingsCache.observeStandingsList(season.id, teamsList)
                .collect {
                    if (it.isEmpty()) createInitialStandingsList(teamsList)
                    _standingsMutableLiveData.value = it
                }
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
                    _weeksMutableLiveData.value = teams?.let {
                        weeksRepo.getApiWeeks(season, it)
                    }
                    timestampCache.updateTimestamp(season.id)
                }
            } catch (e: Throwable) {
                Log.e("AAA", "updateWeeks ${e.message.toString()}")
            }
        }
    }
}