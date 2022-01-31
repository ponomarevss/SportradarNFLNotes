package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class Season(
    @Expose val id: String,
    @Expose val year: Int,
    @Expose val startDate: String,
    @Expose val endDate: String,
    @Expose val status: String,
    @Expose val type: Type
): Parcelable
