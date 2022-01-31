package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class League(
    @Expose val id: String,
    @Expose val name: String,
    @Expose val alias: String
): Parcelable
