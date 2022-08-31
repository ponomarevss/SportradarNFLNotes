package ru.geekbrains.ponomarevss.sportradarnflnotes.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.ISeasonsRepo

class SeasonsViewModel(private val repo: ISeasonsRepo): ViewModel() {

    private val _mutableLiveData: MutableLiveData<List<Season>> = MutableLiveData()
    val liveDataForViewToObserve: LiveData<List<Season>> = _mutableLiveData

}