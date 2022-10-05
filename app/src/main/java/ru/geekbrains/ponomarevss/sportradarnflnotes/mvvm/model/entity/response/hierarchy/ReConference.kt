package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.response.hierarchy

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReConference(
    @Expose val name: String,
    @Expose val divisions: List<ReDivision>
): Parcelable