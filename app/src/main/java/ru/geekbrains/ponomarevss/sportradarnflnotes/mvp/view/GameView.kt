package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface GameView: MvpView {
    fun init()
    fun setScheduled(text: String)
    fun setStatus(text: String)
    fun setHomeName(text: String)
    fun setHomeMarket(text: String)
    fun setHomePoints(text: String)
    fun setAwayName(text: String)
    fun setAwayMarket(text: String)
    fun setAwayPoints(text: String)
    fun updateList()
}