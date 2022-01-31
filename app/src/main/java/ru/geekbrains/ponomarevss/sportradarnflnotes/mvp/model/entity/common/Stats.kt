package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
open class Stats(
    var wins: Int = 0,
    var losses: Int = 0,
    var ties: Int = 0
): Parcelable