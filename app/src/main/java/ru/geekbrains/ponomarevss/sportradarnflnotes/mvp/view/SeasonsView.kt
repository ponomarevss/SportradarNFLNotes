package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface SeasonsView: MvpView {
    fun init()
    fun updateList()
}