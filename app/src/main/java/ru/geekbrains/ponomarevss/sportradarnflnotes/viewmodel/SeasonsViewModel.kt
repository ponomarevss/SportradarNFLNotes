package ru.geekbrains.ponomarevss.sportradarnflnotes.viewmodel

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.DAILY_UPDATE
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.SEASONS_TIMESTAMP
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.ITimestampCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomTimestamp
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.ISeasonsRepo

class SeasonsViewModel(
    private val repo: ISeasonsRepo,
    private val timestampCache: ITimestampCache
) : ViewModel() {

    private val _mutableLiveData: MutableLiveData<List<Season>> = MutableLiveData()
    val liveData: LiveData<List<Season>> = _mutableLiveData

    fun loadInitSeasons() {
        viewModelScope.launch {
            try {
                if (_mutableLiveData.value == null) {
                    _mutableLiveData.value = repo.getCachedSeasons().reversed()
                }
            } catch (e: Throwable) {
                Log.e("AAA", "loadInitSeasons ${e.message.toString()}")
            }
        }
    }

    fun onlineLiveDataObserver(): Observer<Boolean> = Observer<Boolean> {
        updateSeasons(it)
    }

    private fun updateSeasons(isOnline: Boolean) {
        viewModelScope.launch {
            try {
                if (isOnline && !isUpdated()) {
                    _mutableLiveData.value = repo.getApiSeasons().reversed()
                    updateTimestamp()
                }
            } catch (e: Throwable) {
                Log.e("AAA", "updateSeasons ${e.message.toString()}")
            }
        }
    }

    private suspend fun updateTimestamp() {
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            timestampCache.putTimestamp(
                RoomTimestamp(SEASONS_TIMESTAMP, System.currentTimeMillis())
            )
        }
    }

    private suspend fun isUpdated(): Boolean {
        val lastUpdate = withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            timestampCache.getTimestamp(SEASONS_TIMESTAMP)?.timestamp ?: 0
        }
        return System.currentTimeMillis() - lastUpdate < DAILY_UPDATE
    }
}