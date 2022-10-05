package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Week(
    val id: String,
    val sequence: Int,
    val title: String
): Parcelable