package ru.geekbrains.ponomarevss.sportradarnflnotes.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.IWeeksRepo

class SeasonViewModel(private val season: Season, private val repo: IWeeksRepo) : ViewModel() {

    private val _weeksMutableLiveData: MutableLiveData<List<Week>> = MutableLiveData()
    val weeksLiveData: LiveData<List<Week>> = _weeksMutableLiveData

    fun onlineLiveDataObserver(): Observer<Boolean> = Observer<Boolean> {
        getNetworkData(it)
    }

    fun getCachedData() {
        viewModelScope.launch {
            _weeksMutableLiveData.value = repo.getCachedWeeks(season).reversed()
        }
    }

    private fun getNetworkData(isOnline: Boolean) {
        viewModelScope.launch {
            if (isOnline) {
                _weeksMutableLiveData.value = repo.getApiWeeks(season).reversed()
            }
        }
    }
}