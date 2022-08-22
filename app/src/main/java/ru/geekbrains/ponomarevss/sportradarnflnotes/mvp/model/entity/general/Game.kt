package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val id: String,
    val status: String,
    val scheduled: String,
    val home: String,
    val away: String,
    val scoring: List<Int>? = null,
    var isWatched: Boolean = false
): Parcelable