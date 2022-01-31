package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    @Expose val id: String,
    @Expose val status: String,
    @Expose val scheduled: String,
    @Expose val home: Rival,
    @Expose val away: Rival,
    @Expose val scoring: Scoring? = null,
    var isWatched: Boolean = false
): Parcelable
