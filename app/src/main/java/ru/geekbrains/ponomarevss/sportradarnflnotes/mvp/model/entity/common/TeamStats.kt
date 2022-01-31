package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
open class TeamStats(
    var divisionStats: Stats = Stats()
): Stats(), Parcelable