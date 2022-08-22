package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Season(
    val id: String,
    val year: Int,
    val status: String,
    val type: String
): Parcelable
