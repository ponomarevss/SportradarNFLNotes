package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.response.hierarchy

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReHierarchy(
    @Expose val conferences: List<ReConference>
): Parcelable
