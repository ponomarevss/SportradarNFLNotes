package ru.geekbrains.ponomarevss.sportradarnflnotes.viewmodel

import androidx.lifecycle.*
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
            if (_weeksMutableLiveData.value == null) {
                _weeksMutableLiveData.value = repo.getCachedWeeks(season).reversed()
            }
        }
    }

    fun onlineLiveDataObserver(): Observer<Boolean> = Observer<Boolean> {
        updateWeeks(it)
    }

    private fun updateWeeks(isOnline: Boolean) {
        viewModelScope.launch {
            if (isOnline && !isUpdated) {
                _weeksMutableLiveData.value = repo.getApiWeeks(season).reversed()
                isUpdated = true
            }
        }
    }
}