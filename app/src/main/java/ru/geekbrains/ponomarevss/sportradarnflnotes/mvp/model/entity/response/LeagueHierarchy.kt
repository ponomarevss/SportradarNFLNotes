package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class LeagueHierarchy(
    @Expose val league: League,
    @Expose val conferences: List<Conference>
): Parcelable
