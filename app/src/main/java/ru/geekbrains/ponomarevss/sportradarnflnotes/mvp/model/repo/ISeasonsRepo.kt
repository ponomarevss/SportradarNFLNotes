package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season

interface ISeasonsRepo {

    fun getSeasons(): List<Season>
//    fun getSeasons(): Single<List<Season>>
}