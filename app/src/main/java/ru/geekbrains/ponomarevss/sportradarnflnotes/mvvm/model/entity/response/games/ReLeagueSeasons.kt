package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.response.games

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReLeagueSeasons(
    @Expose val seasons: List<ReSeason>
): Parcelable