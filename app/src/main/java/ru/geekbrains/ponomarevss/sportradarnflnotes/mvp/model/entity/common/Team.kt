package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class Team(
    @Expose val id: String,
    @Expose val name: String,
    @Expose val market: String,
    @Expose val alias: String,
    var divisionId: String? = null
//    var stats: TeamStats = TeamStats()
): Parcelable
