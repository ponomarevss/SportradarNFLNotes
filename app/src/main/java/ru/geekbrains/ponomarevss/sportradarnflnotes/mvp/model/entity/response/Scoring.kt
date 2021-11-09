package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class Scoring(
    @Expose val homePoints: Int,
    @Expose val awayPoints: Int,
    @Expose val periods: List<Period>
): Parcelable
