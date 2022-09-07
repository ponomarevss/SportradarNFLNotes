package ru.geekbrains.ponomarevss.sportradarnflnotes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.ISeasonsRepo

class SeasonsViewModel(private val repo: ISeasonsRepo): ViewModel() {

    private val _mutableLiveData: MutableLiveData<List<Season>> = MutableLiveData()
    val liveData: LiveData<List<Season>> = _mutableLiveData

    fun getData() {
        viewModelScope.launch {
            _mutableLiveData.value = repo.getSeasons().reversed()
        }
    }
}