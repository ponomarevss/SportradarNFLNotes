package ru.geekbrains.ponomarevss.sportradarnflnotes.viewmodel

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.HOURLY_UPDATE
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IStandingsCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.ITimestampCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Standings
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.ITeamsRepo
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.IWeeksRepo

class SeasonViewModel(
    private val season: Season,
    private val weeksRepo: IWeeksRepo,
    private val teamsRepo: ITeamsRepo,
    private val standingsCache: IStandingsCache,
    private val timestampCache: ITimestampCache
) : ViewModel() {

    private val _weeksMutableLiveData: MutableLiveData<List<Week>> = MutableLiveData()
    val weeksLiveData: LiveData<List<Week>> = _weeksMutableLiveData

    private val _standingsMutableLiveData: MutableLiveData<List<Standings>> = MutableLiveData()
    val standingsLiveData: LiveData<List<Standings>> = _standingsMutableLiveData

    fun loadInitData() {
        viewModelScope.launch {
            loadInitWeeks()
            loadStandings()
        }
    }

    private suspend fun loadInitWeeks() {
        try {
            if (_weeksMutableLiveData.value == null) {
                _weeksMutableLiveData.value = weeksRepo.getCachedWeeks(season).reversed()
            }
        } catch (e: Throwable) {
            Log.e("AAA", "loadInitWeeks ${e.message.toString()}")
        }
    }


    private suspend fun loadStandings() {
        try {
            if (_standingsMutableLiveData.value == null) {
                val teams = teamsRepo.getCachedTeams()
                _standingsMutableLiveData.value = standingsCache.getStandingsList(season.id, teams)
            }
        } catch (e: Throwable) {
            Log.e("AAA", "loadStandings ${e.message.toString()}")
        }
    }

    fun onlineLiveDataObserver(): Observer<Boolean> = Observer<Boolean> {
        updateWeeks(it)
    }

    private fun updateWeeks(isOnline: Boolean) {
        viewModelScope.launch {
            try {
                if (isOnline && !timestampCache.isUpdated(season.id, HOURLY_UPDATE)) {
                    _weeksMutableLiveData.value = weeksRepo.getApiWeeks(season).reversed()
                    timestampCache.updateTimestamp(season.id)
                }
            } catch (e: Throwable) {
                Log.e("AAA", "updateWeeks ${e.message.toString()}")
            }
        }
    }
}