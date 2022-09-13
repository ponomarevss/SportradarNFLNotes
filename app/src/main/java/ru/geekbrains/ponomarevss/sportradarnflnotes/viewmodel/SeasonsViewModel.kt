package ru.geekbrains.ponomarevss.sportradarnflnotes.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.ISeasonsRepo

class SeasonsViewModel(private val repo: ISeasonsRepo) : ViewModel() {

    private val _mutableLiveData: MutableLiveData<List<Season>> = MutableLiveData()
    val liveData: LiveData<List<Season>> = _mutableLiveData

//    fun getData(isOnline: Boolean) {
//        viewModelScope.launch {
//            _mutableLiveData.value = repo.getSeasons(isOnline).reversed()
//        }
//    }

    fun onlineLiveDataObserver(): Observer<Boolean> = Observer<Boolean> {
        getNetworkData(it)
    }

    fun getCachedData() {
        viewModelScope.launch {
            _mutableLiveData.value = repo.getCachedSeasons().reversed()
        }
    }

    private fun getNetworkData(isOnline: Boolean) {
        viewModelScope.launch {
            if (isOnline) {
                _mutableLiveData.value = repo.getNetworkSeasons().reversed()
            }
        }
    }
}