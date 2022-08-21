package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.games

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReScoring(
    @Expose val homePoints: Int,
    @Expose val awayPoints: Int,
): Parcelable
