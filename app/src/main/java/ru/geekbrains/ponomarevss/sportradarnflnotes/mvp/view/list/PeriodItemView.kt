package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.list

interface PeriodItemView: IItemView {
    fun setPeriodType(text: String)
    fun setNumber(text: String)
    fun setHomePoints(text: String)
    fun setAwayPoints(text: String)
}