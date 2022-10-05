package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Standings(
    val seasonId: String,
    val team: Team,
    var wins: Int = 0,
    var losses: Int = 0,
    var ties: Int = 0,
    var divWins: Int = 0,
    var divLosses: Int = 0,
    var divTies: Int = 0
): Parcelable