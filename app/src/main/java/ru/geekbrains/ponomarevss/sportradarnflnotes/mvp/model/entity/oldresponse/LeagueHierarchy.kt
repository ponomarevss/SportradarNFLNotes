package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.oldresponse

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Conference
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.League

@Parcelize
data class LeagueHierarchy(
    @Expose val league: League,
    @Expose val conferences: List<Conference>
): Parcelable
