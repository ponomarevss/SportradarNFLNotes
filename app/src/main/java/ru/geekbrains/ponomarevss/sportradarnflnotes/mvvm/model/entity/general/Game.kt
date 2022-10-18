package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val id: String,
    val status: String,
    val scheduled: String,
    val home: Team,
    val away: Team,
    var homePoints: Int,
    var awayPoints: Int,
    var isWatched: Boolean = false,
    var rating: Float = 0f
): Parcelable