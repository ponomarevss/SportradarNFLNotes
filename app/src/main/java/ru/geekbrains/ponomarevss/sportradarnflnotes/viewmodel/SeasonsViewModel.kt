package ru.geekbrains.ponomarevss.sportradarnflnotes.viewmodel

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.ISeasonsRepo

class SeasonsViewModel(private val repo: ISeasonsRepo) : ViewModel() {

    private val _mutableLiveData: MutableLiveData<List<Season>> = MutableLiveData()
    val liveData: LiveData<List<Season>> = _mutableLiveData

    private var isUpdated = false

    fun loadInitSeasons() {
        viewModelScope.launch {
            if (_mutableLiveData.value == null) {
                _mutableLiveData.value = repo.getCachedSeasons().reversed()
            }
        }
    }

    fun onlineLiveDataObserver(): Observer<Boolean> = Observer<Boolean> {
        updateSeasons(it)
    }

    private fun updateSeasons(isOnline: Boolean) {
        viewModelScope.launch {
            if (isOnline && !isUpdated) {
                _mutableLiveData.value = repo.getApiSeasons().reversed()
                isUpdated = true
            }
        }
    }
}