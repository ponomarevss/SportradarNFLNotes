package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.response.games

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReSeason(
    @Expose val id: String,
    @Expose val year: Int,
    @Expose val status: String,
    @Expose val type: ReType
): Parcelable
