package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.list

interface StandingsItemView: IItemView {
    fun setTeam(text: String)
    fun setWLT(text: String)
    fun setDivWLT(text: String)
    fun loadLogo(url: String)
}