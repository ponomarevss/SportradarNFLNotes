package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.list

interface SeasonItemView: IItemView {
    fun setYear(text: String)
    fun setStartDate(text: String)
    fun setEndDate(text: String)
    fun setStatus(text: String)
    fun setType(text: String)
}