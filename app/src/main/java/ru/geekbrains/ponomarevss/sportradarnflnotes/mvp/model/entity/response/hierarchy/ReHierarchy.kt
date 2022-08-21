package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.hierarchy

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReHierarchy(
    @Expose val conferences: List<ReConference>
): Parcelable
