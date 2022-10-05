package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Team(
    val id: String,
    val name: String,
    val market: String,
    var alias: String,
    val division: String
): Parcelable