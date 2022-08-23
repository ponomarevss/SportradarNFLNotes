package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.hierarchy

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReTeam(
    @Expose val id: String,
    @Expose val name: String,
    @Expose val market: String,
    @Expose val alias: String,
): Parcelable