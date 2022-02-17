package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rival(
    @Expose val id: String,
    @Expose val name: String,
    @Expose var alias: String,
    @Expose val gameNumber: Int
): Parcelable
