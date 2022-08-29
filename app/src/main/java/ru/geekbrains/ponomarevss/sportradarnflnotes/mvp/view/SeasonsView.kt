package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.appstate.SeasonsAppState

@AddToEndSingle
interface SeasonsView: MvpView {
    fun init()
    fun updateList()
    fun renderData(appState: SeasonsAppState)
}