package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.response.games

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReWeek(
    @Expose val id: String,
    @Expose val sequence: Int,
    @Expose val title: String,
    @Expose val games: List<ReGame>
): Parcelable
