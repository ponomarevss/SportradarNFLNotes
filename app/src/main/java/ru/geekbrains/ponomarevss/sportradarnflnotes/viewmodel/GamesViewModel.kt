package ru.geekbrains.ponomarevss.sportradarnflnotes.viewmodel

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Game
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.IGamesRepo

class GamesViewModel(
    private val season: Season,
    private val week: Week,
    private val repo: IGamesRepo
) : ViewModel() {

    private val _mutableLiveData: MutableLiveData<List<Game>> = MutableLiveData()
    val liveData: LiveData<List<Game>> = _mutableLiveData

    private var isUpdated = false

    fun loadInitGames() {
        viewModelScope.launch {
            try {
                if (_mutableLiveData.value == null) {
                    _mutableLiveData.value = repo.getCachedGames(week)
                }
            } catch (e: Throwable) {
                Log.e("AAA", "loadInitGames ${e.message.toString()}")
            }
        }
    }

    fun onlineLiveDataObserver(): Observer<Boolean> = Observer<Boolean> { updateGames(it) }

    private fun updateGames(isOnline: Boolean) {
        viewModelScope.launch {
            try {
                if (isOnline && !isUpdated) {
                    _mutableLiveData.value = repo.getApiGames(season, week)
                    isUpdated = true
                }
            } catch (e: Throwable) {
                Log.e("AAA", "updateGames ${e.message.toString()}")
            }
        }
    }
}