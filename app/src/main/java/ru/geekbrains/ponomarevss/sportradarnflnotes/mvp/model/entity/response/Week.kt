package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class Week(
    @Expose val id: String,
    @Expose val sequence: Int,
    @Expose val title: String,
    @Expose val games: List<Game>
): Parcelable
