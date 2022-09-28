package ru.geekbrains.ponomarevss.sportradarnflnotes.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Game
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.IGamesRepo
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.ITeamsRepo

class GamesViewModel(
    private val week: Week,
    private val gamesRepo: IGamesRepo,
    private val teamsRepo: ITeamsRepo
) : ViewModel() {

    private val _mutableLiveData: MutableLiveData<List<Game>> = MutableLiveData()
    val liveData: LiveData<List<Game>> = _mutableLiveData

    fun loadInitGames() {
        viewModelScope.launch {
            try {
                if (_mutableLiveData.value == null) {
                    _mutableLiveData.value = gamesRepo.getCachedGames(week)
                }
            } catch (e: Throwable) {
                Log.e("AAA", "loadInitGames ${e.message.toString()}")
            }
        }
    }
}