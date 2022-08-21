package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.oldresponse

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.League
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Season

@Parcelize
data class LeagueSeasons(
    @Expose val league: League,
    @Expose val seasons: List<Season>
): Parcelable
