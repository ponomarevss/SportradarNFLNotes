package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.games

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReGame(
    @Expose val id: String,
    @Expose val status: String,
    @Expose val scheduled: String,
    @Expose val home: ReTeamId,
    @Expose val away: ReTeamId,
    @Expose val scoring: ReScoring? = null,
): Parcelable
