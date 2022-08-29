package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.appstate

import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season

sealed class SeasonsAppState {

    data class Success(val data: List<Season>?) : SeasonsAppState()
    data class Error(val error: Throwable) : SeasonsAppState()
    data class Loading(val progress: Int?) : SeasonsAppState()
}