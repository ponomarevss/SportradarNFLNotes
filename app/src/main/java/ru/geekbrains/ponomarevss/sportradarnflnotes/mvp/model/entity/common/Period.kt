package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class Period(
    @Expose val periodType: String,
    @Expose val id: String,
    @Expose val number: Int,
    @Expose val homePoints: Int,
    @Expose val awayPoints: Int
): Parcelable
