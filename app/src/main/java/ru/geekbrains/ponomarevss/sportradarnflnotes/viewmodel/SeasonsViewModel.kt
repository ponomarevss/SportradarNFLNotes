package ru.geekbrains.ponomarevss.sportradarnflnotes.viewmodel

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.DAILY_UPDATE
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.SEASONS_TIMESTAMP
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.TEAMS_TIMESTAMP
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.WEEKLY_UPDATE
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.ITimestampCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.ISeasonsRepo
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.ITeamsRepo

class SeasonsViewModel(
    private val seasonsRepo: ISeasonsRepo,
    private val teamsRepo: ITeamsRepo,
    private val timestampCache: ITimestampCache
) : ViewModel() {

    private val _mutableLiveData: MutableLiveData<List<Season>> = MutableLiveData()
    val liveData: LiveData<List<Season>> = _mutableLiveData

    fun loadInitSeasons() {
        viewModelScope.launch {
            try {
                if (_mutableLiveData.value == null) {
                    _mutableLiveData.value = seasonsRepo.getCachedSeasons().reversed()
                }
            } catch (e: Throwable) {
                Log.e("AAA", "loadInitSeasons ${e.message.toString()}")
            }
        }
    }

    fun onlineLiveDataObserver(): Observer<Boolean> = Observer<Boolean> {
        updateData(it)
    }

    private fun updateData(isOnline: Boolean) {
        viewModelScope.launch {
            updateSeasons(isOnline)
            updateTeams(isOnline)
        }
    }

    private suspend fun updateSeasons(isOnline: Boolean) {
        try {
            if (isOnline && !timestampCache.isUpdated(SEASONS_TIMESTAMP, DAILY_UPDATE)) {
                _mutableLiveData.value = seasonsRepo.getApiSeasons().reversed()
                timestampCache.updateTimestamp(SEASONS_TIMESTAMP)
            }
        } catch (e: Throwable) {
            Log.e("AAA", "updateSeasons ${e.message.toString()}")
        }
    }

    private suspend fun updateTeams(isOnline: Boolean) {
        try {
            if (isOnline && !timestampCache.isUpdated(TEAMS_TIMESTAMP, WEEKLY_UPDATE)) {
                teamsRepo.getApiTeams()
                timestampCache.updateTimestamp(TEAMS_TIMESTAMP)
            }
        } catch (e: Throwable) {
            Log.e("AAA", "updateTeams ${e.message.toString()}")
        }
    }
}