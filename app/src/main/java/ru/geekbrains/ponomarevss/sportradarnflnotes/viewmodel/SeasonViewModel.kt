package ru.geekbrains.ponomarevss.sportradarnflnotes.viewmodel

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.IWeeksRepo

class SeasonViewModel(private val season: Season, private val repo: IWeeksRepo) : ViewModel() {

    private val _weeksMutableLiveData: MutableLiveData<List<Week>> = MutableLiveData()
    val weeksLiveData: LiveData<List<Week>> = _weeksMutableLiveData

    private var isUpdated = false

    fun loadInitWeeks() {
        viewModelScope.launch {
            try {
                if (_weeksMutableLiveData.value == null) {
                    _weeksMutableLiveData.value = repo.getCachedWeeks(season).reversed()
                    Log.e("AAA", "data from repo.getCachedWeeks ${_weeksMutableLiveData.value}")
                }
            } catch (e: Throwable) {
                Log.e("AAA", "loadInitWeeks ${e.message.toString()}")
            }
        }
    }

    fun onlineLiveDataObserver(): Observer<Boolean> = Observer<Boolean> {
        updateWeeks(it)
    }

    private fun updateWeeks(isOnline: Boolean) {
        viewModelScope.launch {
            try {
                if (isOnline && !isUpdated) {
                    _weeksMutableLiveData.value = repo.getApiWeeks(season).reversed()
                    Log.e("AAA", "data from api")
                    isUpdated = true
                }
            } catch (e: Throwable) {
                Log.e("AAA", "updateWeeks ${e.message.toString()}")
            }
        }
    }
}