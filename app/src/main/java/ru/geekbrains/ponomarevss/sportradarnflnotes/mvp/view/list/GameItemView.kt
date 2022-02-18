package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.list

interface GameItemView: IItemView {
    fun setStatus(text: String)
    fun setScheduled(text: String)
    fun setHome(text: String)
    fun setAway(text: String)
    fun setScoring(text: String)
    fun loadHomeLogo(url: String)
    fun loadAwayLogo(url: String)
}